package com.projects.prjsem2service.presentation.mapper;

import com.projects.prjsem2service.persistence.entity.CategoryEntity;
import com.projects.prjsem2service.persistence.entity.ConsignmentDetailsEntity;
import com.projects.prjsem2service.persistence.entity.ConsignmentEntity;
import com.projects.prjsem2service.persistence.entity.ProductEntity;
import com.projects.prjsem2service.presentation.dto.ExcelDTO;
import com.projects.prjsem2service.presentation.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Locale;
import java.util.UUID;

@Component
public class ExcelMapper {

    public CategoryEntity mapExcelDtoToCategoryEntity(ExcelDTO excelDTO){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(UUID.randomUUID().toString());
        categoryEntity.setName(excelDTO.getCategoryName());
        categoryEntity.setCode(excelDTO.getCategoryCode().toUpperCase(Locale.ROOT));
        categoryEntity.setStatus(Constants.STATUS_ACTIVE);
        if(StringUtils.isBlank(excelDTO.getHsd())){
            categoryEntity.setHsd(Constants.FALSE);
        }else{
            categoryEntity.setHsd(Constants.TRUE);
        }
        categoryEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        return categoryEntity;
    }

    public ProductEntity mapExcelDtoToCategoryEntity(ExcelDTO excelDTO,CategoryEntity category){
        ProductEntity entity = new ProductEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setName(excelDTO.getProductName());
        entity.setCode(excelDTO.getProductCode().toUpperCase(Locale.ROOT));
        entity.setBrand(excelDTO.getBrand());
        entity.setMadeIn(excelDTO.getMadeIn());
        entity.setPrice(excelDTO.getSalePrice());
        entity.setCat(category);
        entity.setStatus(Constants.STATUS_ACTIVE);
        entity.setCreatedAt(new Date(System.currentTimeMillis()));
        return entity;
    }

    public ConsignmentDetailsEntity mapExcelDtoToConsignmentDetailsEntity(ExcelDTO excelDTO,ProductEntity product,ConsignmentEntity consignment){
        ConsignmentDetailsEntity consignmentDetailsEntity = new ConsignmentDetailsEntity();
        consignmentDetailsEntity.setId(UUID.randomUUID().toString());
        consignmentDetailsEntity.setNumberProductsInBox(excelDTO.getNumberProductsInBox());
        consignmentDetailsEntity.setNumberOfBoxes(excelDTO.getNumberOfBoxes());
        consignmentDetailsEntity.setImportPrice(excelDTO.getImportPrice());
        consignmentDetailsEntity.setNsx(null);
        consignmentDetailsEntity.setHsd(null);
        consignmentDetailsEntity.setProduct(product);
        consignmentDetailsEntity.setConsignment(consignment);
        consignmentDetailsEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        consignmentDetailsEntity.setAmount(excelDTO.getImportPrice()*excelDTO.getImportPrice());
        return consignmentDetailsEntity;
    }



}
