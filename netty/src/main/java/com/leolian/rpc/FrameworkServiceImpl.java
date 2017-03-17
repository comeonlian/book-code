package com.leolian.rpc;

import java.lang.reflect.Method;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import com.leolian.media.Media;
import com.leolian.thrift.demo.FrameworkService;
import com.leolian.thrift.demo.ThriftRequest;
import com.leolian.thrift.demo.ThriftResponse;

public class FrameworkServiceImpl implements FrameworkService.Iface {

	@Override
	public ThriftResponse execute(ThriftRequest request) throws TException {
		Object content = Media.execute(request);
		ThriftResponse response = new ThriftResponse();
		byte[] result = null;
		try {
			result = buildResponse(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setBody(result);
		return response;
	}

	private byte[] buildResponse(Object content) throws Exception {
		Method write = content.getClass().getMethod("write", TProtocol.class);
		TMemoryBuffer buffer = new TMemoryBuffer(1024);
		TProtocol proto = new TBinaryProtocol(buffer);
		write.invoke(content, proto);
		return buffer.getArray();
	}
	
	
}
