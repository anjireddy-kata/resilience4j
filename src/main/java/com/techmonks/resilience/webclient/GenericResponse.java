package com.techmonks.resilience.webclient;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {
    private T responseEntity;
}
