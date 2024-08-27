package org.obs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidCredentialsException extends RuntimeException {
    private final String message;
}
