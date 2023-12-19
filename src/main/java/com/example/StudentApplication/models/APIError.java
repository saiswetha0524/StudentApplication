package com.example.StudentApplication.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIError {

    private StatusEnum status;

    private String errorMessage;

  //  private AE errorResponse;

    private String requestURI;

}
