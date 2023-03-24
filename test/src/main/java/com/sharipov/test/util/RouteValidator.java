package com.sharipov.test.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sharipov.test.models.Route;
import com.sharipov.test.services.RoutesService;

@Component
public class RouteValidator implements Validator{
	
	private final RoutesService rootsService;

	public RouteValidator(RoutesService rootsService) {
		this.rootsService = rootsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
	
		return Route.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Route root = (Route) target;
		if(rootsService.findByCodeAndName(root).isPresent()) {
			if(!(rootsService.findByCodeAndName(root).get().equals(root))) {
				errors.rejectValue("code", "", "Root with these code and name is alreadu exists!");
				errors.rejectValue("name", "", "Root with these code and name is alreadu exists!");
			}
		}
	}
}
