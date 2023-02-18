package com.projects.prjsem2service.presentation.service.impl;

import com.projects.prjsem2service.persistence.entity.ConsignmentEntity;
import com.projects.prjsem2service.persistence.entity.ReceiptEntity;
import com.projects.prjsem2service.persistence.entity.WarehouseEntity;
import com.projects.prjsem2service.persistence.repository.ConsignmentRepository;
import com.projects.prjsem2service.persistence.repository.ReceiptRepository;
import com.projects.prjsem2service.persistence.repository.WarehouseRepository;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.ReceiptWarehouseDTO;
import com.projects.prjsem2service.presentation.dto.RecieptDTO;
import com.projects.prjsem2service.presentation.service.RecieptService;
import com.projects.prjsem2service.presentation.util.Constants;
import com.projects.prjsem2service.presentation.util.RecieptType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RecieptServiceImpl implements RecieptService {
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ConsignmentRepository consignmentRepository;
    @Override
    public ErrorCodeResponse createReciept(RecieptDTO request) {
        try {
            WarehouseEntity fromWarehouse = warehouseRepository.getById(request.getWarehouseId());
            WarehouseEntity toWarehouse = warehouseRepository.getById(request.getToWarehouseId());
            ConsignmentEntity consignment = consignmentRepository.getById(request.getConsignmentId());
            ReceiptEntity entity = new ReceiptEntity();
            entity.setId(UUID.randomUUID().toString());
            entity.setCode(RecieptType.HOA_DON.name()+"-"+RecieptType.NHAP.name()+"-"+ ThreadLocalRandom.current().nextInt());
            entity.setWarehouseNameSent(fromWarehouse.getName());
            entity.setWarehouseAddressSent(fromWarehouse.getAddress());
            entity.setWarehouseNameReceive(toWarehouse.getName());
            entity.setWarehouseAddressReceive(toWarehouse.getAddress());
            if(Constants.STATUS_ACTIVE.equalsIgnoreCase(request.getStatus())){
                entity.setType(RecieptType.VAN_CHUYEN.name());
            }
            if(Constants.STATUS_SALED.equalsIgnoreCase(request.getStatus())){
                entity.setType(RecieptType.XUAT.name());
            }
            entity.setRecieptConsignment(consignment);
            entity.setCreatedAt(new Date(System.currentTimeMillis()));
            receiptRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);

        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }

    @Override
    public List<ReceiptWarehouseDTO> getReceiptByWarehouseCode(String name) {
        try {
            List<ReceiptEntity> sent = receiptRepository.findAllByWarehouseNameSent(name);
            List<ReceiptEntity> receive = receiptRepository.findAllByWarehouseNameReceive(name);
            List<ReceiptEntity> receiptEntities = new ArrayList<>();
            receiptEntities.addAll(sent);
            receiptEntities.addAll(receive);
            List<ReceiptWarehouseDTO> recieptDTOS = new ArrayList<>();
            for(ReceiptEntity entity : receiptEntities){
                ReceiptWarehouseDTO dto = new ReceiptWarehouseDTO()
                        .withCode(entity.getCode())
                        .withConsignmentCode(entity.getRecieptConsignment().getCode())
                        .withConsignmentId(entity.getRecieptConsignment().getId())
                        .withWarehouseNameSent(entity.getWarehouseNameSent())
                        .withWarehouseAddressSent(entity.getWarehouseAddressSent())
                        .withWarehouseNameReceive(entity.getWarehouseNameReceive())
                        .withWarehouseAddressReceive(entity.getWarehouseAddressReceive())
                        .withType(entity.getType());
                recieptDTOS.add(dto);

            }
            return recieptDTOS;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
