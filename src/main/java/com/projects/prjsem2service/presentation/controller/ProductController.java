package com.projects.prjsem2service.presentation.controller;

import com.projects.prjsem2service.presentation.dto.CategoryDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.ProductDTO;
import com.projects.prjsem2service.presentation.service.CategoryService;
import com.projects.prjsem2service.presentation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/get-all-product")
    public List<ProductDTO> getListProduct(){
        return productService.getAllProduct();
    }

    @PostMapping(value = "/save-product")
    public ErrorCodeResponse saveCategory(@RequestBody ProductDTO productDTO){
        return productService.saveProduct(productDTO);
    }
    @GetMapping(value ="/get-product")
    public ProductDTO getProductById(@RequestParam(value = "id") String id){
        return productService.getProductById(id);
    }
}
