package com.lobox.assignment.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCountFilter", urlPatterns = "/*")
public class RequestCountFilter extends OncePerRequestFilter {

    private static final AtomicInteger requestCount = new AtomicInteger(0);

    public static int getRequestCount() {
        return requestCount.get();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        requestCount.incrementAndGet();
        filterChain.doFilter(request, response);
    }
}
