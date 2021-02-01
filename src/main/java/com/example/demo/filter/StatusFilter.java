package com.example.demo.filter;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import java.io.IOException;


@Log4j2

public class StatusFilter implements Filter {
    @Override

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("inside the filter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
