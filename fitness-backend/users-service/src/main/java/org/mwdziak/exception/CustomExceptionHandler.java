package org.mwdziak.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ExpiredJwtException.class, JwtException.class, RuntimeException.class,
            UsernameNotFoundException.class, BadCredentialsException.class})
    public ProblemDetail handleSecurityExceptions(Exception e) {
        ProblemDetail problemDetail;
        if (e instanceof ExpiredJwtException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                "Jwt expired"
            );
        } else if (e instanceof UsernameNotFoundException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                "User not found"
            );
        } else if (e instanceof BadCredentialsException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                "Bad credentials"
            );

        } else if (e instanceof JwtException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                "Invalid token"
            );
        } else {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(500),
                "Internal server error"
            );
        }

        return problemDetail;
    }
}
