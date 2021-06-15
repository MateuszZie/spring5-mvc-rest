package org.mateuszziebura.spring5mvcrest.api.v1.mapper;

import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5mvcrest.api.v1.model.VendorDTO;
import org.mateuszziebura.spring5mvcrest.domain.Vendor;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {

    public static final String NAME = "mateusz";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void vendorToVendorDto() {
        Vendor vendor = new Vendor();

        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);

        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    void vendorDtoToVendor() {
        VendorDTO vendorDTO = new VendorDTO();

        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);

        assertEquals(NAME, vendor.getName());
    }
}