package com.meriga.mfinance.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Configuration
//public class CORSFilter extends OncePerRequestFilter {
//    private static final Log LOG = LogFactory.getLog(CORSFilter.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
//            LOG.trace("Sending Header....");
//            // CORS "pre-flight" request
//            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
//            response.addHeader("Access-Control-Allow-Headers", "Origin");
//            response.addHeader("Access-Control-Allow-Headers", "X-Auth-Token");
//            response.addHeader("Access-Control-Allow-Headers", "Authorization");
//            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
//            response.addHeader("Access-Control-Max-Age", "1");
//        }
//        filterChain.doFilter(request, response);
//    }
//
//}
