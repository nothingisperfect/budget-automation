package app.security.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.naming.AuthenticationException;

@Getter
public class InvalidJwtAuthException extends AuthenticationException {
    private HttpStatus httpStatus;

    public InvalidJwtAuthException(String msg) {
        super(msg);
    }

    public InvalidJwtAuthException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}