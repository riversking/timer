package com.rivers.oauth.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author riversking
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    /**
     * token错误时进入到这里
     * @param request
     * @param response
     * @param authException
     * @throws ServletException
     */

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws ServletException {

        Map map = new HashMap(16);
        map.put("code", 401);
        map.put("msg", authException.getMessage());
        map.put("rsp", "");
        //map.put("path", request.getServletPath());
        map.put("timestamp", System.currentTimeMillis());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}