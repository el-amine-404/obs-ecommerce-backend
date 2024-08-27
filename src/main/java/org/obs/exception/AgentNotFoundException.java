package org.obs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AgentNotFoundException extends RuntimeException {
    private final String message;
}
