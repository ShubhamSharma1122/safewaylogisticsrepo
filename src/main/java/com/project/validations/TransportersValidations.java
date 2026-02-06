//package com.project.validations;
//
//import org.springframework.validation.Validator;
//
//import com.project.dto.TransportersDTO;
//import com.project.service.TransportersService;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class TransportersValidations implements Validator {
//	private final TransportersService transportersService;
//
//	/*
//	 * 
//	 * @Override public boolean supports(Class<?> clazz) { return
//	 * CategoryDTO.class.equals(clazz); }
//	 * 
//	 * @Override public void validate(Object target, Errors errors) { CategoryDTO
//	 * categoryDto = (CategoryDTO) target;
//	 * 
//	 * if (categoryDto.getUuid() != null &&
//	 * !categoryService.existsByUuidAndActive(categoryDto.getUuid(), null)) {
//	 * errors.rejectValue("name", "400",
//	 * messageByLocaleService.getMessage("category.not.found.id", new Object[] {
//	 * categoryDto.getUuid() })); return; }
//	 * 
//	 * if (categoryService.isExistsCategory(categoryDto)) {
//	 * errors.rejectValue("name", "400",
//	 * messageByLocaleService.getMessage("name.exists", new Object[] { "Category"
//	 * })); }
//	 * 
//	 * }
//	 */
//
//	@Override
//	public boolean supports(Class<?> clazz) {
//
//		return TransportersDTO.class.equals(clazz);
//	}
//
////	@Override
////	public void validate(Object target, Errors errors) {
////		TransportersDTO transportersDTO = (TransportersDTO) target;
////		if(transportersDTO.getId()!=null&&transportersService.existsByidAndActive(transportersDTO.getId(), null)) {
////        
////		 
////	}
//
//}
