package com.projects.prjsem2service.presentation.service.impl;

import com.projects.prjsem2service.persistence.entity.CategoryEntity;
import com.projects.prjsem2service.persistence.repository.CategoryRepository;
import com.projects.prjsem2service.presentation.dto.CategoryDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.service.CategoryService;
import com.projects.prjsem2service.presentation.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getAllCategory() {
        try {
            List<CategoryEntity> entities = categoryRepository.findAllByStatus(Constants.STATUS_ACTIVE);
            List<CategoryDTO> categoryResponses = new ArrayList<>();
            for(CategoryEntity category : entities){
                CategoryDTO response = new CategoryDTO(category.getId(),category.getName(),category.getCode(),category.getStatus());
                categoryResponses.add(response);
            }
            return categoryResponses;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        CategoryEntity category = categoryRepository.getById(id);
        CategoryDTO response = new CategoryDTO(category.getId(),category.getName(),category.getCode(),category.getStatus());
        return response;
    }

    @Override
    public ErrorCodeResponse saveCategory(CategoryDTO request) {
        try {
            boolean checkNameExits = categoryRepository.existsByName(request.getName());
            boolean checkCodeExits = categoryRepository.existsByCode(request.getCode());

            CategoryEntity entity = new CategoryEntity();
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
            entity.setName(request.getName());
            entity.setCode(request.getCode().toUpperCase(Locale.ROOT));
            entity.setStatus(Constants.STATUS_ACTIVE);
            entity.setCreatedAt(new Date(System.currentTimeMillis()));
            categoryRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CategoryDTO> getAllCategoryDeleted() {
        try {
            List<CategoryEntity> entities = categoryRepository.findAllByStatus(Constants.STATUS_UNACTIVE);
            List<CategoryDTO> categoryResponses = new ArrayList<>();
            for(CategoryEntity category : entities){
                CategoryDTO response = new CategoryDTO(category.getId(),category.getName(),category.getCode(),category.getStatus());
                categoryResponses.add(response);
            }
            return categoryResponses;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ErrorCodeResponse deteleCategory(String id) {
        try {
            Optional<CategoryEntity> category = categoryRepository.findById(id);
            CategoryEntity entity = category.get();
            entity.setStatus(Constants.STATUS_UNACTIVE);
            categoryRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }

    @Override
    public ErrorCodeResponse restoreCategory(String id) {
        try {
            Optional<CategoryEntity> category = categoryRepository.findById(id);
            CategoryEntity entity = category.get();
            entity.setStatus(Constants.STATUS_ACTIVE);
            categoryRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }

    @Override
    public ErrorCodeResponse validateName(String name) {
        try {
            boolean entity = categoryRepository.existsByName(name);
            if(entity){
                return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,Constants.NAME_VALID_DESC);
            }else {
                return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,Constants.NAME_DUPLICATE_DESC);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }

    @Override
    public ErrorCodeResponse validateCode(String code) {
        try {
            boolean entity = categoryRepository.existsByName(code);
            if(entity){
                return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,Constants.CODE_VALID_DESC);
            }else {
                return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,Constants.CODE_DUPLICATE_DESC);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }
}
