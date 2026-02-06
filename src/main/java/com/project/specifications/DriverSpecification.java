package com.project.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.project.dto.DriverFilterDTO;
import com.project.entity.Driver;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DriverSpecification implements Specification<Driver> {

	private static final long serialVersionUID = 1L;

	private final DriverFilterDTO filter;

	@Override
	public Predicate toPredicate(Root<Driver> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<>();


		if (!StringUtils.isBlank(filter.getName())) {
			predicates.add(cb.like(cb.lower(root.get("driverName")), "%" + filter.getName().toLowerCase() + "%"));
		}


		if (filter.getContact() != null) {
			predicates.add(cb.equal(root.get("driverContact"), filter.getContact()));
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}
}
