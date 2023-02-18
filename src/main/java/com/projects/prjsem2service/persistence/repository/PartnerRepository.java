package com.projects.prjsem2service.persistence.repository;

import com.projects.prjsem2service.persistence.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity,String> {
    List<PartnerEntity> findAllByStatus(String status);
    Optional<PartnerEntity> findById(String id);
    boolean existsByName(String name);
    boolean existsByCode(String code);
}
