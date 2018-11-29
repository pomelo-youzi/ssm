package com.itheima.controller;

import com.itheima.domian.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;

    private Date visitTime;//开始时间
    private Class clazz;//访问类；
    private Method method;//访问的方法

    //    前置通知
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
//获取开始时间；
        visitTime = new Date();
//        获取要访问的类；
        clazz = jp.getTarget().getClass();
//        获取要访问的方法名字；
        String methodName = jp.getSignature().getName();
//        获取访问方法的参数；
        Object[] args = jp.getArgs();
//        判断是否有参；
        if (args == null || args.length == 0) {
//            无参
            method = clazz.getMethod(methodName);
        } else {
//            有参
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < classArgs.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后置通知
     *
     * @param jp
     * @throws NoSuchMethodException
     */
    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
//        获取访问时长；
        Long time = new Date().getTime() - visitTime.getTime();
        String url="";
//        获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
//        获取@RequestMapping注解
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
//获取类上的@RequestMapping注解的值；
//            获取方法上的@RequestMapping注解的值；
                String[] clazzValue = clazzAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = clazzValue[0] + methodValue[0];
                    //        获取访问的ip；
                    String ip = request.getRemoteAddr();
//        获取当前操作对象；
//        从上下文获取当前操作对象；
                    SecurityContext context = SecurityContextHolder.getContext();
//        获取当前对象
                    User user = (User) context.getAuthentication().getPrincipal();
//        获取用户名
                    String username = user.getUsername();
//        创建syslog对象；
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUsername(username);
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
