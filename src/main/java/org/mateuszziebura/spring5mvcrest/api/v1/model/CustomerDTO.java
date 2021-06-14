package org.mateuszziebura.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private String firstName;
    private String lastName;
    @JsonProperty("customer_url")
    private String customerUrl;
}
