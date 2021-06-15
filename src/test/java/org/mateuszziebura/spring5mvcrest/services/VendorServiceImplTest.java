package org.mateuszziebura.spring5mvcrest.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5mvcrest.api.v1.mapper.VendorMapper;
import org.mateuszziebura.spring5mvcrest.api.v1.model.VendorDTO;
import org.mateuszziebura.spring5mvcrest.domain.Vendor;
import org.mateuszziebura.spring5mvcrest.repositories.VendorRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VendorServiceImplTest {

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    Vendor vendor;
    VendorDTO vendorDTO;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
        vendor = new Vendor();
        vendor.setName("mateusz");
        vendor.setId(1L);
        vendorDTO = new VendorDTO();
        vendorDTO.setName("arkadiusz");
    }

    @Test
    void getVendors() {

        List<Vendor> vendors = Arrays.asList(new Vendor(), vendor);
        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS =vendorService.getVendors();

        assertEquals(2,vendorDTOS.size());

    }

    @Test
    void getVendorById() {

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        assertEquals("mateusz", vendorDTO.getName());
    }

    @Test
    void createNewVendor() {
        when(vendorRepository.save(any())).thenReturn(vendor);

        VendorDTO retVendorDto = vendorService.createNewVendor(vendorDTO);

        assertEquals("mateusz", retVendorDto.getName());
    }

    @Test
    void deleteVendor() {
        vendorService.deleteVendor(1L);

        verify(vendorRepository).deleteById(anyLong());
    }

    @Test
    void updateVendor() {
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor));
        when(vendorRepository.save(any())).thenReturn(vendor);

        VendorDTO vendorDTO2 = vendorService.updateVendor(1L,vendorDTO);

        assertEquals("arkadiusz",vendorDTO2.getName());
    }
}