package org.mateuszziebura.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.web.JsonPath;

@Getter
@Setter
public class VendorDTO {

    private String name;
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
