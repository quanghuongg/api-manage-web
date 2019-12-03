package com.mange.api.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {
    @JsonProperty("code")
    private int code = 0;

    @JsonProperty("message")
    private String message = "success";

    @JsonProperty("data")
    private Object data;
}
