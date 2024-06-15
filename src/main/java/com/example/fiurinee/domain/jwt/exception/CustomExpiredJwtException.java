package com.example.fiurinee.domain.jwt.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CustomExpiredJwtException extends ExpiredJwtException {
    private final String message;

    public CustomExpiredJwtException(String message, ExpiredJwtException source) {
        super(source.getHeader(), source.getClaims(), source.getMessage());
        this.message = message;
    }
}
