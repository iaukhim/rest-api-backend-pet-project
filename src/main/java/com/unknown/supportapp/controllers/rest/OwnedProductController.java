package com.unknown.supportapp.controllers.rest;

import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.services.OwnedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("owned-products")
public class OwnedProductController {

    private OwnedProductService ownedProductService;

    @Autowired
    public OwnedProductController(OwnedProductService ownedProductService) {
        this.ownedProductService = ownedProductService;
    }

    @PutMapping("/serial")
    public String changeSerial(@RequestParam String oldValue, @RequestParam String newValue){
        ownedProductService.changeSerial(oldValue, newValue);
        return "success";
    }

    @GetMapping("/serial")
    public boolean checkSerial(@RequestParam String serialNumber){
        return ownedProductService.checkSerial(serialNumber);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        ownedProductService.deleteById(id);
    }

    @GetMapping("/{id}")
    public OwnedProductDto getById(@PathVariable Long id){
        OwnedProductDto ownedProductDto = ownedProductService.loadById(id);
        return ownedProductDto;
    }

    @GetMapping("/{id}/model")
    public String loadModelById(@PathVariable Long id){
        return ownedProductService.loadModelById(id);
    }

    @PostMapping("")
    public void saveOwnedProduct(@RequestBody OwnedProductDto ownedProductDto){
        ownedProductService.saveProduct(ownedProductDto);
    }


}
