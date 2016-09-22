package com.game.smvc.controller;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.game.smvc.entity.Test;
import com.game.util.SessionUtils;

@Controller
@RequestMapping("/smvc/test")
public class TestController {

    private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("constants");
    /**
     * 测试MVC返回view
     */
    @RequestMapping("/testview")
    public ModelAndView testview() {
        return new ModelAndView("/lmuch/index");
    }

    /**
     * 测试MVC返回JSON
     */
    @RequestMapping("testjson")
    @ResponseBody
    public Test testjson(@RequestParam("hh") String hh) {
        System.out.println(hh);
        Test test = new Test("1234", "testjson");
        return test;
    }
    
    /**
     * 测试上传参数
     */
    @RequestMapping("testParam")
    @ResponseBody
    public Test testParam(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("hh"));
        Test test = new Test("123", "testParam");
        return test;
    }
    
    /**
     * 测试Session
     */
    @RequestMapping("testSession")
    @ResponseBody
    public Test testSession(HttpServletRequest request, HttpServletResponse response) {
        Test test = SessionUtils.getSessionTest(request);
        if (test == null) {
            test = new Test("12345", Thread.currentThread().getStackTrace()[1].getMethodName());
            HttpSession session = request.getSession();
            session.setAttribute("test", test);
        }
        return test;
    }
    
    public static void main(String[] args) {
        System.out.println(bundle.getString("memcache_servers"));
        System.out.println(bundle.getString("JUMP_URL"));
        System.out.println(bundle.getString("PIC_PATH"));
        System.out.println(bundle.getString("PIC_URL"));
    }
}
