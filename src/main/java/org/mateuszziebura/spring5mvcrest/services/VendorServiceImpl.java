package org.mateuszziebura.spring5mvcrest.services;

import org.mateuszziebura.spring5mvcrest.api.v1.mapper.VendorMapper;
import org.mateuszziebura.spring5mvcrest.api.v1.model.VendorDTO;
import org.mateuszziebura.spring5mvcrest.controllers.v1.VendorController;
import org.mateuszziebura.spring5mvcrest.domain.Vendor;
import org.mateuszziebura.spring5mvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        if(vendorRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException();
        }
        VendorDTO vendorDTO =vendorMapper.vendorToVendorDto(vendorRepository.findById(id).get());
        vendorDTO.setVendorUrl(getVendorUrl(id));
        return vendorDTO;
    }

    private String getVendorUrl(Long id){
        return VendorController.BASE_URL + "/" +id;
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        Vendor vendor = vendorRepository.save(vendorMapper.vendorDtoToVendor(vendorDTO));
        VendorDTO savedVendorDTO = vendorMapper.vendorToVendorDto(vendor);
        vendorDTO.setVendorUrl(VendorController.BASE_URL+"/"+vendor.getId());
        return savedVendorDTO;
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorRepository.findById(id).get();
        vendor.setName(vendorDTO.getName());
        return vendorMapper.vendorToVendorDto(vendorRepository.save(vendor));
    }
}
