package org.obs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductStatusException extends RuntimeException {
    private final String message;
}
