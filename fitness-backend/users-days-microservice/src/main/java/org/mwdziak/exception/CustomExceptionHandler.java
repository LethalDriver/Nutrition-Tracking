package org.mwdziak.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
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
        } else {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(403),
                "Forbidden"
            );
        }

        return problemDetail;
    }
}
