package com.projects.prjsem2service.persistence.repository;

import com.projects.prjsem2service.persistence.entity.ConsignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsignmentRepository extends JpaRepository<ConsignmentEntity,String> {
    List<ConsignmentEntity> findAllByStatus(String status);

    Integer countAllByStatus(String status);
}
