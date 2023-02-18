package com.projects.prjsem2service.presentation.service;

import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.PartnerDTO;

import java.util.List;

public interface PartnerService {
    List<PartnerDTO> getAllPartner();
    List<PartnerDTO> getAllPartnerDeleted();
    ErrorCodeResponse savePartner(PartnerDTO request);

    PartnerDTO getPartnerById(String id);
    ErrorCodeResponse detelePartner(String id);
    ErrorCodeResponse restorePartner(String id);
}
