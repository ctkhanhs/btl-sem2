package com.projects.prjsem2service.presentation.service;



import com.projects.prjsem2service.presentation.dto.CategoryDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategory();
    CategoryDTO getCategoryById(String id);
    List<CategoryDTO> getAllCategoryDeleted();
    ErrorCodeResponse saveCategory(CategoryDTO request);
    ErrorCodeResponse deteleCategory(String id);
    ErrorCodeResponse restoreCategory(String id);

    ErrorCodeResponse validateName(String name);
    ErrorCodeResponse validateCode(String code);

}
