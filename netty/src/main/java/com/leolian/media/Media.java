package com.leolian.media;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import com.google.protobuf.ByteString;
import com.leolian.http.entity.RequestParameter;
import com.leolian.proto.RequestParamProtobuf.RequestParam;
import com.leolian.thrift.demo.ThriftRequest;
import com.leolian.utils.JsonUtils;

/**
 * cmd(操作) -- ExecuteBean(需要执行的bean，以及方法)
 * @author Lian
 */
public class Media {
	
	public static Map<String, ExecuteBean> beanMethods = new HashMap<String, ExecuteBean>();

	@SuppressWarnings("rawtypes")
	public static Object execute(Object obj) {
		Object result = null;
		String cmd = "";
		Object paramObj = null;
		try{
			if(obj instanceof RequestParam){
				RequestParam param = (RequestParam) obj;
				cmd = param.getCmd();
			}else if(obj instanceof RequestParameter){
				RequestParameter parameter = (RequestParameter) obj;
				cmd = parameter.getCommand();
			}else if(obj instanceof ThriftRequest){
				ThriftRequest param = (ThriftRequest) obj;
				cmd = param.getCommond();
			}
			
			ExecuteBean executeBean = beanMethods.get(cmd);
			Object targetBean = executeBean.getBean();
			Method targetMethod = executeBean.getMethod();
			//需要执行的方法的参数类型
			Class<?> paramType = targetMethod.getParameterTypes()[0];
			
			if(obj instanceof RequestParam){
				//方法的参数类型的构造器
				Constructor[] paramTypeConstructors = paramType.getDeclaredConstructors();
				Constructor c = null; //构造器
				for (Constructor constructor : paramTypeConstructors) {
					//选择protobuf的参数为boolean类型的构造器
					if(constructor.getParameterTypes()[0].getName().equals("boolean")){
						c = constructor;
					}
				}
				if(null!=c)
					c.setAccessible(true);
				paramObj = c.newInstance(true); //构造器对象
				ByteString requestConetent = ((RequestParam)obj).getRequestContent(); 
				//获取protobuf中的parseFrom方法
				Method parseFromMethod = paramType.getMethod("parseFrom", ByteString.class);
				//给参数对象设置值
				paramObj = parseFromMethod.invoke(paramObj, requestConetent);
				
			}else if(obj instanceof RequestParameter){
				RequestParameter parameter = (RequestParameter) obj;
				//paramObj = JsonUtils.jsonToBean(parameter.getParameter().toString(), targetMethod.getParameterTypes()[0]);
				paramObj = parameter.getParameter();
			}else if(obj instanceof ThriftRequest){
				ThriftRequest request = (ThriftRequest) obj;
				byte[] param = request.getRequestParam();
				Method paramMethod = paramType.getMethod("read", TProtocol.class);
				paramObj = paramType.newInstance();
				TMemoryBuffer buffer = new TMemoryBuffer(param.length);
				buffer.write(param);
				TProtocol prot = new TBinaryProtocol(buffer);
				paramMethod.invoke(paramObj, prot);
			}
			
			//调用目标方法
			result = targetMethod.invoke(targetBean, paramObj);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
