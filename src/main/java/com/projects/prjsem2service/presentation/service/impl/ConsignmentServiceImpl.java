package com.projects.prjsem2service.presentation.service.impl;

import com.projects.prjsem2service.persistence.entity.*;
import com.projects.prjsem2service.persistence.repository.*;
import com.projects.prjsem2service.presentation.dto.*;
import com.projects.prjsem2service.presentation.mapper.ExcelMapper;
import com.projects.prjsem2service.presentation.service.ConsignmentService;
import com.projects.prjsem2service.presentation.service.RecieptService;
import com.projects.prjsem2service.presentation.util.Constants;
import com.projects.prjsem2service.presentation.util.RecieptType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ConsignmentServiceImpl implements ConsignmentService {
    @Autowired
    private ExcelMapper excelMapper;
    @Autowired
    private ConsignmentRepository consignmentRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ConsignmentDetailsRepository consignmentDetailsRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public ErrorCodeResponse createConsignment(MultipartFile excelFile) {
        try {
            Integer quantity = 0;
            double importTotal = 0;
            double exportTotal = 0;
            WarehouseEntity warehouse = warehouseRepository.findByCode("KC");
            ConsignmentEntity consignment = new ConsignmentEntity();
            consignment.setId(UUID.randomUUID().toString());
            consignment.setCode(Constants.CONSIGNMENT_CODE+"-"+ThreadLocalRandom.current().nextInt());
            consignment.setStatus(Constants.STATUS_NEW);
            consignment.setWarehouse(warehouse);
            consignment.setCreatedAt(new Date(System.currentTimeMillis()));
            consignmentRepository.save(consignment);
            if(excelFile.isEmpty()){
                return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
            }
            List<ExcelDTO> excelDTOList = readExcelFile(excelFile);
            for (ExcelDTO excelDTO : excelDTOList){
                quantity += excelDTO.getNumberOfBoxes();
                importTotal += (excelDTO.getImportPrice()*excelDTO.getNumberOfBoxes());
                exportTotal += (excelDTO.getSalePrice()*excelDTO.getNumberOfBoxes());
                createConsignDetails(excelDTO,consignment);
            }
            consignment.setQuantity(quantity);
            consignment.setExportTotal(exportTotal);
            consignment.setImportTotal(importTotal);
            consignmentRepository.save(consignment);
            createReciept(consignment);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }

    @Override
    public List<ConsignmentDTO> newConsignmentList() {
        try {
            List<ConsignmentEntity> newList = consignmentRepository.findAllByStatus(Constants.STATUS_NEW);
            List<ConsignmentDTO> consignmentDTOList = new ArrayList<>();
            for(ConsignmentEntity entity : newList){
                ConsignmentDTO dto = new ConsignmentDTO()
                        .withId(entity.getId())
                        .withCode(entity.getCode())
                        .withQuantity(entity.getQuantity())
                        .withStatus(entity.getStatus())
                        .withWarehouseId(entity.getWarehouse().getId())
                        .withImportAmount(entity.getImportTotal())
                        .withExportAmount(entity.getExportTotal());
                consignmentDTOList.add(dto);
            }
            return consignmentDTOList;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ConsignmentDTO> consignmentList() {
        try {
            List<ConsignmentEntity> newList = consignmentRepository.findAllByStatus(Constants.STATUS_ACTIVE);
            List<ConsignmentDTO> consignmentDTOList = new ArrayList<>();
            for(ConsignmentEntity entity : newList){
                ConsignmentDTO dto = new ConsignmentDTO()
                        .withId(entity.getId())
                        .withCode(entity.getCode())
                        .withQuantity(entity.getQuantity())
                        .withStatus(entity.getStatus())
                        .withWarehouseId(entity.getWarehouse().getId())
                        .withWarehouseName(entity.getWarehouse().getName())
                        .withWarehouseCode(entity.getWarehouse().getCode())
                        .withImportAmount(entity.getImportTotal())
                        .withExportAmount(entity.getExportTotal());
                consignmentDTOList.add(dto);
            }
            return consignmentDTOList;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ErrorCodeResponse updateConsignment(ConsignmentDTO dto) {
        try {
            ConsignmentEntity entity = consignmentRepository.getById(dto.getId());
            WarehouseEntity warehouse = warehouseRepository.getById(dto.getToWarehouseId());
            entity.setStatus(dto.getStatus());
            entity.setWarehouse(warehouse);
            consignmentRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }

    }

    @Override
    public ConsignmentDTO getOne(String id) {
        ConsignmentEntity entity = consignmentRepository.getById(id);
        return new ConsignmentDTO()
                .withId(entity.getId())
                .withCode(entity.getCode())
                .withCode(entity.getStatus())
                .withWarehouseId(entity.getWarehouse().getId());
    }

    @Override
    public List<ConsignmentDetailsDTO> getAllConsignmentDetails(String id) {
        try {
            ConsignmentEntity consignment = consignmentRepository.getById(id);
            List<ConsignmentDetailsEntity> consignmentDetailsEntityList = consignment.getConsignmentDetailsEntities();
            List<ConsignmentDetailsDTO> consignmentDetailsDTOS = new ArrayList<>();
            for(ConsignmentDetailsEntity details : consignmentDetailsEntityList){
                ConsignmentDetailsDTO dto = new ConsignmentDetailsDTO()
                        .withId(details.getId())
                        .withProductName(details.getProduct().getName())
                        .withProductCode(details.getProduct().getCode())
                        .withCategoryName(details.getProduct().getCat().getName())
                        .withNumberOfBoxes(details.getNumberOfBoxes())
                        .withNumberProductsInBox(details.getNumberProductsInBox())
                        .withImportPrice(details.getImportPrice())
                        .withExportPrice(details.getProduct().getPrice());
                consignmentDetailsDTOS.add(dto);
            }
            return consignmentDetailsDTOS;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ReportDTO getReport() {
        try {
            Integer saled = consignmentRepository.countAllByStatus(Constants.STATUS_SALED);
            Integer active = consignmentRepository.countAllByStatus(Constants.STATUS_ACTIVE);
            List<ConsignmentEntity> saledList = consignmentRepository.findAllByStatus(Constants.STATUS_SALED);
            List<ConsignmentEntity> list = consignmentRepository.findAll();
            Double totalImportAmount = Double.valueOf(0);
            Double totalExportAmount = Double.valueOf(0);
            for(ConsignmentEntity entity : list){
                totalImportAmount+= entity.getImportTotal();
            }
            for(ConsignmentEntity entity : saledList){
                totalExportAmount+=entity.getExportTotal();
            }
            return new ReportDTO()
                    .withActive(active)
                    .withSaled(saled)
                    .withTotalImportAmount(totalImportAmount)
                    .withTotalExportAmount(totalExportAmount)
                    .withRevenue(totalExportAmount-totalImportAmount);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private List<ExcelDTO> readExcelFile(MultipartFile excelFile){
        try {
            List<ExcelDTO> list = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                ExcelDTO dto = new ExcelDTO();

                XSSFRow row = worksheet.getRow(i);

                dto.setProductCode(row.getCell(Constants.CELL_PRODUCT_CODE).getStringCellValue());
                dto.setProductName(row.getCell(Constants.CELL_PRODUCT_NAME).getStringCellValue());
                dto.setCategoryCode(row.getCell(Constants.CELL_CATEGORY_CODE).getStringCellValue());
                dto.setCategoryName(row.getCell(Constants.CELL_CATEGORY_NAME).getStringCellValue());
                dto.setBrand(row.getCell(Constants.CELL_BRAND).getStringCellValue());
                dto.setMadeIn(row.getCell(Constants.CELL_MADE_IN).getStringCellValue());
                dto.setSalePrice(row.getCell(Constants.CELL_SALE_PRICE).getNumericCellValue());
                dto.setImportPrice(row.getCell(Constants.CELL_IMPORT_PRICE).getNumericCellValue());
                dto.setNumberProductsInBox((int) row.getCell(Constants.CELL_MUNBER_PRODUCTS_IN_BOX).getNumericCellValue());
                dto.setNumberOfBoxes((int) row.getCell(Constants.CELL_NUMBER_OF_BOXES).getNumericCellValue());
//                dto.setNsx(row.getCell(Constants.CELL_NSX).getStringCellValue());
//                dto.setHsd(row.getCell(Constants.CELL_HSD).getStringCellValue());
                list.add(dto);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void createConsignDetails(ExcelDTO excelDTO,ConsignmentEntity consignment){
        try {
            ProductEntity checkExitsProduct = productRepository.findByCode(excelDTO.getProductCode().toUpperCase(Locale.ROOT));
            CategoryEntity checkExitsCategory = categoryRepository.findByCode(excelDTO.getCategoryCode().toUpperCase(Locale.ROOT));
            CategoryEntity category = new CategoryEntity();
            ProductEntity product = new ProductEntity();
            if(Objects.isNull(checkExitsCategory)){
                category = excelMapper.mapExcelDtoToCategoryEntity(excelDTO);
                categoryRepository.save(category);
            }
            else {
                category = checkExitsCategory;
            }
            if(Objects.isNull(checkExitsProduct)){
                product = excelMapper.mapExcelDtoToCategoryEntity(excelDTO,category);
                productRepository.save(product);
            }
            else {
                product = checkExitsProduct;
            }
            ConsignmentDetailsEntity consignmentDetailsEntity = excelMapper.mapExcelDtoToConsignmentDetailsEntity(excelDTO,product,consignment);
            consignmentDetailsRepository.save(consignmentDetailsEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createReciept(ConsignmentEntity consignment){
        ReceiptEntity entity = new ReceiptEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setCode(RecieptType.HOA_DON.name()+"-"+RecieptType.NHAP.name()+"-"+ThreadLocalRandom.current().nextInt());
        entity.setWarehouseNameSent("");
        entity.setWarehouseAddressSent("");
        entity.setWarehouseNameReceive("");
        entity.setWarehouseAddressReceive("");
        entity.setType(RecieptType.NHAP.name());
        entity.setRecieptConsignment(consignment);
        entity.setCreatedAt(new Date(System.currentTimeMillis()));
        receiptRepository.save(entity);
    }
}
