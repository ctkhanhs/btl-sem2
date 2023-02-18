package com.projects.prjsem2service.presentation.service;

import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProduct();
    List<ProductDTO> getAllProductDeleted();
    ErrorCodeResponse saveProduct(ProductDTO request);

    ProductDTO getProductById(String id);
    ErrorCodeResponse deteleProduct(String id);
    ErrorCodeResponse restoreProduct(String id);

}
