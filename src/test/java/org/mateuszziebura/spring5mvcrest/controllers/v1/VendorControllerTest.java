package org.mateuszziebura.spring5mvcrest.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5mvcrest.api.v1.model.VendorDTO;
import org.mateuszziebura.spring5mvcrest.controllers.RestResponseEntityExceptionHandler;
import org.mateuszziebura.spring5mvcrest.domain.Vendor;
import org.mateuszziebura.spring5mvcrest.services.ResourceNotFoundException;
import org.mateuszziebura.spring5mvcrest.services.VendorService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mateuszziebura.spring5mvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;

class VendorControllerTest {
    public static final String NAME = "mateusz";
    public static final String URL = "url";
    public static final Long ID = 1l;
    public static final String URI = "/"+VendorController.BASE_URL;
    @Mock
    VendorService vendorService;
    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;
    VendorDTO vendorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
        vendorDTO = new VendorDTO();
        vendorDTO.setVendorUrl(URL);
        vendorDTO.setName(NAME);
    }

    @Test
    void getAllVendors() throws Exception{
        List<VendorDTO> vendors = Arrays.asList(new VendorDTO(),new VendorDTO());

        when(vendorService.getVendors()).thenReturn(vendors);

        mockMvc.perform(get(URI)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors",hasSize(2)));
    }

    @Test
    void getVendor() throws Exception{


        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get(URI+"/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME
                )))
                .andExpect(jsonPath("$.vendor_url", equalTo(URL)));
    }
    @Test
    void getVendorNotFound() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get(URI+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveVendor() throws Exception{
        when(vendorService.createNewVendor(any())).thenReturn(vendorDTO);

        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME
                )))
                .andExpect(jsonPath("$.vendor_url", equalTo(URL)));
    }

    @Test
    void deleteVendor() throws Exception{
        mockMvc.perform(delete(URI+"/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendor(anyLong());
    }

    @Test
    void updateVendor() throws Exception{
        when(vendorService.updateVendor(anyLong(),any())).thenReturn(vendorDTO);

        mockMvc.perform(put(URI+"/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(NAME)));
    }
}