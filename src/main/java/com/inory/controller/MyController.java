package com.inory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inory.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author inory
 * @create 2021-03-17 17:43
 */
@Controller
@RequestMapping("/test")
public class MyController {

    //RequestMapping注解的方法相当于servlet的doGet,doPost
    @RequestMapping(value = {"/some.do", "/first.do"}, method = RequestMethod.GET)
    public ModelAndView doSome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "springmvc获取的值");
        //request.setAttribute("msg","springmvc获取的值");
        mv.addObject("fun", "执行doSome方法");
        //request.setAttribute("fun", "执行doSome方法");
        //mv.setViewName("/WEB-INF/view/show.jsp");
        //request.getdispatcher("/show.jsp").forward(request,response);
        mv.setViewName("show");
        return mv;
    }

    @RequestMapping(value = {"/other.do", "/second.do"}, method = RequestMethod.POST)
    public ModelAndView doOther() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "springmvc获取的值");
        mv.addObject("fun", "执行doOther方法");
        mv.setViewName("other");
        return mv;
    }

    /*@RequestMapping(value = "/others.do")
    public ModelAndView doOtherWithParams(HttpServletRequest request,
                                          HttpServletResponse response,
                                          HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "springmvc获取的值"+request.getParameter("name"));
        mv.addObject("fun", "执行doOther方法");
        mv.setViewName("other");
        return mv;
    }*/

    @RequestMapping(value = "/others.do")
    public ModelAndView doOther(@RequestParam(value = "rname",required = false) String name,
                                @RequestParam(value = "rage",required = false) Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "springmvc获取的值"+name);
        mv.addObject("fun", "执行doOther方法"+age);
        mv.setViewName("other");
        return mv;
    }

    @RequestMapping(value = "/receiveObject.do")
    public ModelAndView doOther(Student student) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "name的值"+student.getName());
        mv.addObject("fun", "age的值"+student.getAge());
        mv.setViewName("other");
        return mv;
    }

    @RequestMapping(value = "/returnString.do")
    public String doReturnString(HttpServletRequest request,String name,Integer age) {
        System.out.println("name="+name+",age="+age);
        request.setAttribute("msg",name);
        request.setAttribute("fun",age);
        return "other";
    }

    @RequestMapping(value = "/returnVoid-ajax.do")
    public void doReturnVoidAjax(HttpServletResponse response, String name, Integer age) throws IOException {
        System.out.println("name="+name+",age="+age);
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        String json = "";
        if (student != null) {
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(student);
            System.out.println("student转换的json"+json);
        }
        //@ResponseBody注解代替下面三句话输出数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }

    @ResponseBody
    @RequestMapping(value = "/returnStudentJson.do")
    public Student doReturnStudentJson(String name, Integer age) {
        Student student = new Student();
        student.setName("李四");
        student.setAge(24);
        return student;//会被框架转成json
    }

    @ResponseBody
    @RequestMapping(value = "/returnStudentJsonArray.do")
    public List<Student> doReturnStudentList(String name, Integer age) {
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setName("李四");
        student.setAge(24);
        list.add(student);
        student = new Student();
        student.setName("王五");
        student.setAge(23);
        list.add(student);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/returnStringData.do",produces = "text/plain;charset=utf-8")
    public String doStringData(String name, Integer age) {
        return "hello springmvc,返回对象,表示数据";
    }
}
