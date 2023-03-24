package com.sharipov.test.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sharipov.test.models.Root;
import com.sharipov.test.services.RootsService;

@Component
public class RootValidator implements Validator{
	
	private final RootsService rootsService;

	public RootValidator(RootsService rootsService) {
		this.rootsService = rootsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
	
		return Root.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Root root = (Root) target;
		if(rootsService.findByCodeAndName(root).isPresent()) {
			if(!(rootsService.findByCodeAndName(root).get().equals(root))) {
				errors.rejectValue("code", "", "Root with these code and name is alreadu exists!");
				errors.rejectValue("name", "", "Root with these code and name is alreadu exists!");
			}
		}
	}
}
