package com.projects.prjsem2service.presentation.service.impl;

import com.projects.prjsem2service.persistence.entity.ConsignmentDetailsEntity;
import com.projects.prjsem2service.persistence.entity.ConsignmentEntity;
import com.projects.prjsem2service.persistence.entity.ProductEntity;
import com.projects.prjsem2service.persistence.repository.ConsignmentDetailsRepository;
import com.projects.prjsem2service.persistence.repository.ConsignmentRepository;
import com.projects.prjsem2service.persistence.repository.ProductRepository;
import com.projects.prjsem2service.presentation.dto.ConsignmentDetailsDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.service.ConsignmentDetailsService;
import com.projects.prjsem2service.presentation.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;

@Service
public class ConsignmentDetailsServiceImpl implements ConsignmentDetailsService {
    @Autowired
    private ConsignmentRepository consignmentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ConsignmentDetailsRepository consignmentDetailsRepository;
    @Override
    public ErrorCodeResponse saveConsignmentDetails(ConsignmentDetailsDTO request) {
        try {
            ConsignmentEntity consignment = consignmentRepository.getById(request.getConsignmentId());
            ProductEntity product = productRepository.getById(request.getProductId());
            ConsignmentDetailsEntity consignmentDetailsEntity = new ConsignmentDetailsEntity();
            consignmentDetailsEntity.setId(UUID.randomUUID().toString());
            consignmentDetailsEntity.setNumberProductsInBox(request.getNumberProductsInBox());
            consignmentDetailsEntity.setNumberOfBoxes(request.getNumberOfBoxes());
            consignmentDetailsEntity.setImportPrice(request.getImportPrice());
            consignmentDetailsEntity.setNsx(request.getNsx());
            consignmentDetailsEntity.setHsd(request.getHsd());
            consignmentDetailsEntity.setProduct(product);
            consignmentDetailsEntity.setConsignment(consignment);
            consignmentDetailsEntity.setCreatedAt(new Date(System.currentTimeMillis()));
            consignmentDetailsEntity.setAmount(request.getImportPrice()*request.getImportPrice());
            consignmentDetailsRepository.save(consignmentDetailsEntity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }
}
