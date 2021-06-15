package org.mateuszziebura.spring5mvcrest.services;

import org.mateuszziebura.spring5mvcrest.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    void  deleteVendor(Long id);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

}
