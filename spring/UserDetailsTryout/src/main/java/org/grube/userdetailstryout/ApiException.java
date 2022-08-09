package org.grube.userdetailstryout;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiException {
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private String message = "";
    private List<SubError> subErrors = new ArrayList<>();

    public ApiException(String message) {
        this.message = message;
    }

    public ApiException(String message, List<SubError> subErrors) {
        this(message);
        this.subErrors = subErrors;
    }

    public boolean addSubError(String node, String message) {
        return subErrors.add(new SubError(node, message));
    }

    @RequiredArgsConstructor
    @Getter
    @Setter
    class SubError {
        private final String node;
        private final String message;
    }
}

