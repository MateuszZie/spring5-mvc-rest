package org.mateuszziebura.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mateuszziebura.spring5mvcrest.api.v1.model.VendorDTO;
import org.mateuszziebura.spring5mvcrest.domain.Vendor;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDto(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}
