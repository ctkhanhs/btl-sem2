package com.projects.prjsem2service.presentation.service.impl;


import com.projects.prjsem2service.persistence.entity.PartnerEntity;
import com.projects.prjsem2service.persistence.repository.PartnerRepository;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.PartnerDTO;
import com.projects.prjsem2service.presentation.service.PartnerService;
import com.projects.prjsem2service.presentation.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public List<PartnerDTO> getAllPartner() {
        try {
            List<PartnerEntity> entities = partnerRepository.findAllByStatus(Constants.STATUS_ACTIVE);
            List<PartnerDTO> partnerDTOList = new ArrayList<>();
            for (PartnerEntity partner : entities) {
                PartnerDTO response = new PartnerDTO(partner.getId(), partner.getName(), partner.getCode(), partner.getAddress(), partner.getPhone(), partner.getStatus());
                partnerDTOList.add(response);
            }
            return partnerDTOList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PartnerDTO> getAllPartnerDeleted() {
        try {
            List<PartnerEntity> entities = partnerRepository.findAllByStatus(Constants.STATUS_UNACTIVE);
            List<PartnerDTO> partnerDTOList = new ArrayList<>();
            for (PartnerEntity partner : entities) {
                PartnerDTO response = new PartnerDTO(partner.getId(), partner.getName(), partner.getCode(), partner.getAddress(), partner.getPhone(), partner.getStatus());
                partnerDTOList.add(response);
            }
            return partnerDTOList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ErrorCodeResponse savePartner(PartnerDTO request) {
        try {
            boolean checkNameExits = partnerRepository.existsByName(request.getName());
            boolean checkCodeExits = partnerRepository.existsByCode(request.getCode());

            PartnerEntity entity = new PartnerEntity();
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
            entity.setId(UUID.randomUUID().toString());
            entity.setName(request.getName());
            entity.setCode(request.getCode().toUpperCase(Locale.ROOT));
            entity.setAddress(request.getAddress());
            entity.setPhone(request.getPhone());
            entity.setStatus(Constants.STATUS_ACTIVE);
            entity.setCreatedAt(new Date(System.currentTimeMillis()));
            partnerRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED, null);
        }
    }

    @Override
    public PartnerDTO getPartnerById(String id) {
        try {
            PartnerEntity partner = partnerRepository.getById(id);
            PartnerDTO response = new PartnerDTO(partner.getId(), partner.getName(), partner.getCode(), partner.getAddress(), partner.getPhone(), partner.getStatus());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ErrorCodeResponse detelePartner(String id) {
        try {
            Optional<PartnerEntity> partner = partnerRepository.findById(id);
            PartnerEntity entity = partner.get();
            entity.setStatus(Constants.STATUS_UNACTIVE);
            partnerRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED, null);
        }
    }

    @Override
    public ErrorCodeResponse restorePartner(String id) {
        try {
            Optional<PartnerEntity> partner = partnerRepository.findById(id);
            PartnerEntity entity = partner.get();
            entity.setStatus(Constants.STATUS_ACTIVE);
            partnerRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED, null);
        }
    }
}
