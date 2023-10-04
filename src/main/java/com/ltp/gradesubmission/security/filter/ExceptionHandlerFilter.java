package com.ltp.gradesubmission.security.filter;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ltp.gradesubmission.exceptions.EntityNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// always be first to handle upcoming exception from following filters
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException entityNotFoundException) {
            createResponse(response, HttpServletResponse.SC_NOT_FOUND, "Could not find username");
        } catch (JWTVerificationException verificationException) {
            createResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "JWT not valid");
        } catch (RuntimeException runtimeException) {
            createResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
        }
    }

    private static void createResponse(HttpServletResponse response, int httpServletResponseStatus, String message) throws IOException {
        response.setStatus(httpServletResponseStatus);
        response.getWriter().write(message);
        response.getWriter().flush();
    }
}
