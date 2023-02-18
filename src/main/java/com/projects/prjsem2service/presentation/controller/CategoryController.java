package com.projects.prjsem2service.presentation.controller;

import com.projects.prjsem2service.presentation.dto.CategoryDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/get-all-category")
    public List<CategoryDTO> getListCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping(value = "/save-category")
    public ErrorCodeResponse saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @GetMapping(value = "/get-category")
    public CategoryDTO getCategoryById(@RequestParam(value = "id") String id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping(value = "/validate-name")
    public ErrorCodeResponse validateName(@RequestParam(value = "name") String name) {
        return categoryService.validateName(name);
    }

    @GetMapping(value = "/validate-code")
    public ErrorCodeResponse validateCode(@RequestParam(value = "code") String code) {
        return categoryService.validateCode(code);
    }

}
