package com.projects.prjsem2service.presentation.service.impl;

import com.projects.prjsem2service.persistence.entity.CategoryEntity;
import com.projects.prjsem2service.persistence.entity.ProductEntity;
import com.projects.prjsem2service.persistence.repository.CategoryRepository;
import com.projects.prjsem2service.persistence.repository.ProductRepository;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.ProductDTO;
import com.projects.prjsem2service.presentation.service.ProductService;
import com.projects.prjsem2service.presentation.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<ProductDTO> getAllProduct() {
        try {
            List<ProductEntity> entities = productRepository.findAllByStatus(Constants.STATUS_ACTIVE);
            List<ProductDTO> dtoList = new ArrayList<>();
            for(ProductEntity product : entities){
                ProductDTO dto = new ProductDTO();
                dto.setId(product.getId());
                dto.setName(product.getName());
                dto.setCode(product.getCode());
                dto.setCategoryName(product.getCat().getName());
                dto.setBrand(product.getBrand());
                dto.setMadeIn(product.getMadeIn());
                dto.setPrice(product.getPrice());
                dto.setImage(product.getImage());
                dtoList.add(dto);
            }
            return dtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProductDTO> getAllProductDeleted() {
        try {
            List<ProductEntity> entities = productRepository.findAllByStatus(Constants.STATUS_UNACTIVE);
            List<ProductDTO> dtoList = new ArrayList<>();
            for(ProductEntity product : entities){
                ProductDTO dto = new ProductDTO();
                dto.setId(product.getId());
                dto.setName(product.getName());
                dto.setCode(product.getCode());
                dto.setCategoryName(product.getCat().getName());
                dto.setBrand(product.getBrand());
                dto.setMadeIn(product.getMadeIn());
                dto.setPrice(product.getPrice());
                dtoList.add(dto);
            }
            return dtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ErrorCodeResponse saveProduct(ProductDTO request) {
        try {
            ProductEntity entity = new ProductEntity();
            boolean checkNameExits = productRepository.existsByName(request.getName());
            boolean checkCodeExits = productRepository.existsByCode(request.getCode());

            if(!StringUtils.isEmpty(request.getId())){
                entity.setId(request.getId());
            }else {
                if(checkNameExits){
                    return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,Constants.NAME_DUPLICATE_DESC);
                }
                if(checkCodeExits){
                    return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,Constants.CODE_DUPLICATE_DESC);
                }
                entity.setId(UUID.randomUUID().toString());
            }
            CategoryEntity category = categoryRepository.getById(request.getCategoryId());
            entity.setName(request.getName());
            entity.setCode(request.getCode().toUpperCase(Locale.ROOT));
            entity.setBrand(request.getBrand());
            entity.setMadeIn(request.getMadeIn());
            entity.setPrice(request.getPrice());
            entity.setCat(category);
            entity.setStatus(Constants.STATUS_ACTIVE);
            entity.setCreatedAt(new Date(System.currentTimeMillis()));
            productRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }

    @Override
    public ProductDTO getProductById(String id) {
        ProductEntity product = productRepository.getById(id);
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCode(product.getCode());
        dto.setCategoryName(product.getCat().getName());
        dto.setBrand(product.getBrand());
        dto.setMadeIn(product.getMadeIn());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCat().getId());
        dto.setCategoryName(product.getCat().getName());
        return dto;
    }

    @Override
    public ErrorCodeResponse deteleProduct(String id) {
        try {
            Optional<ProductEntity> product = productRepository.findById(id);
            ProductEntity entity = product.get();
            entity.setStatus(Constants.STATUS_UNACTIVE);
            productRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }

    @Override
    public ErrorCodeResponse restoreProduct(String id) {
        try {
            Optional<ProductEntity> product = productRepository.findById(id);
            ProductEntity entity = product.get();
            entity.setStatus(Constants.STATUS_ACTIVE);
            productRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }
}
