package com.project.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.project.dto.TransportersFilterDto;
import com.project.entity.Transporters;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransporterSpecification implements Specification<Transporters> {

	private static final long serialVersionUID = 1L;

	private final TransportersFilterDto filter;

	@Override
	public Predicate toPredicate(Root<Transporters> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<>();


		if (!StringUtils.isBlank(filter.getOwnerName())) {
			predicates.add(cb.like(cb.lower(root.get("ownerName")), "%" + filter.getOwnerName().toLowerCase() + "%"));
		}


		if (!StringUtils.isBlank(filter.getTransportName())) {
			predicates.add(
					cb.like(cb.lower(root.get("transportName")), "%" + filter.getTransportName().toLowerCase() + "%"));
		}


		if (filter.getContactNumber() != null) {
			predicates.add(cb.equal(root.get("contactNumber"), filter.getContactNumber()));
		}


		if (!StringUtils.isBlank(filter.getEmail())) {
			predicates.add(cb.like(cb.lower(root.get("email")), "%" + filter.getEmail().toLowerCase() + "%"));
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}
}
