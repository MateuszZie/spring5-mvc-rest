package org.mateuszziebura.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.web.JsonPath;

@Getter
@Setter
public class VendorDTO {

    @ApiModelProperty(value = "Name is required", required = true)
    private String name;
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
