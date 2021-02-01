package com.example.demo.Interceptor;

import com.example.demo.model.ExecutionTime;
import com.example.demo.repository.ExecutionTimeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class LocationInterceptor implements HandlerInterceptor {
    @Autowired
    ExecutionTimeRepository executionTimeRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(" --PreHandle");
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(" --postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("--afterCompletion");
        long executionTime =  System.currentTimeMillis() - (long) request.getAttribute("startTime");
        log.info("execution time in Millis : {}", executionTime);

        String URI = request.getRequestURI();
        int secondIndexOfSlash = URI.indexOf("/",URI.indexOf("/")+1);

        String requestURI = URI.substring(secondIndexOfSlash+1,URI.length());
        executionTimeRepository.save(new ExecutionTime(requestURI,executionTime));
    }
}
