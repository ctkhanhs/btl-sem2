package com.projects.prjsem2service.persistence.repository;

import com.projects.prjsem2service.persistence.entity.ConsignmentDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsignmentDetailsRepository extends JpaRepository<ConsignmentDetailsEntity,String> {
}
