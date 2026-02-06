package com.project.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.entity.Transporters;

public interface TransportersRepo extends JpaRepository<Transporters, Long>, JpaSpecificationExecutor<Transporters> {
	Page<Transporters> findAllByActiveTrue(Specification<Transporters> spec, Pageable pageable);
}
