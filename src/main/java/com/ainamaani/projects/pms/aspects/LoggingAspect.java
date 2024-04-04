//package com.ainamaani.projects.pms.aspects;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//public class LoggingAspect {
//
//    @Pointcut("execution(* com.ainamaani.projects.pms.controllers.*.*(..)) && @annotation(requestMapping)")
//    public void controllerMethods(RequestMapping requestMapping) {
//    }
//
//    @Before("controllerMethods(requestMapping)")
//    public void logBefore(JoinPoint joinPoint, RequestMapping requestMapping) {
//        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
//        String method = request.getMethod();
//        String path = request.getRequestURI();
//        Object[] args = joinPoint.getArgs();
//        System.out.println("Request Method: " + method);
//        System.out.println("Request Path: " + path);
//        if (method.equals("POST") && args.length > 1) {
//            System.out.println("Request Body: " + args[1]);
//        }
//    }
//}
