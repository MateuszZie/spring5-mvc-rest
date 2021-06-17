package org.mateuszziebura.spring5mvcrest.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mateuszziebura.spring5mvcrest.api.v1.model.VendorDTO;
import org.mateuszziebura.spring5mvcrest.api.v1.model.VendorListDTO;
import org.mateuszziebura.spring5mvcrest.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Vendor")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "For Vendors click here" , notes = "Check this vendors")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors(){
        return new VendorListDTO(vendorService.getVendors());
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendor(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO saveVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id){
        vendorService.deleteVendor(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.updateVendor(id,vendorDTO);
    }
}
