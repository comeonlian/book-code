package com.game.docking.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

/**
 * 动态加载方法
 */
public class DyMethodUtil {

    public static Object invokeMethod(String jexlExp, Map<String, Object> map) {
        JexlEngine jexl = new JexlEngine();
        Expression e = jexl.createExpression(jexlExp);
        JexlContext jc = new MapContext();
        for (String key : map.keySet()) {
            jc.set(key, map.get(key));
        }
        if (null == e.evaluate(jc)) {
            return "";
        }
        return e.evaluate(jc);
    }
    
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = scriptEngineManager.getEngineFactories();
        if (engineFactories.size() == 0) {
            System.out.println("本JVM尚不支持任何脚本引擎");
            return;
        }

        System.out.println("本JVM支持的脚本引擎有:");
        for (ScriptEngineFactory engineFactory : engineFactories) {
            System.out.println("引擎名称:" + engineFactory.getEngineName());
            System.out.println("\t可被ScriptEngineManager识别的名称:" + engineFactory.getNames());
            System.out.println("\t该引擎支持的脚本语言名称:" + engineFactory.getLanguageName());
            System.out.println("\t是否线程安全:" + engineFactory.getParameter("THREADING"));
        }
        new Date().getTime();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        String exp = "(new Date()).getTime().toString();";
//        scriptEngine.put("a", Math.random()*(-10));
//        scriptEngine.put("b", Math.random()*10);
        Object result = scriptEngine.eval(exp);
        System.out.println(exp + " === " + result);
    }
        
}
