package org.mateuszziebura.spring5mvcrest.api.v1.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VendorListDTO {
    List<VendorDTO> vendors;
}
