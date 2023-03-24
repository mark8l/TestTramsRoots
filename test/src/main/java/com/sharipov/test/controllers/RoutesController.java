package com.sharipov.test.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharipov.test.models.Route;
import com.sharipov.test.services.RoutesService;
import com.sharipov.test.util.RouteValidator;

@Controller
@RequestMapping("/trams")
public class RoutesController {
	
	private final RoutesService rootsService;
	private RouteValidator rootValidator;
	
	public RoutesController(RoutesService rootsService, RouteValidator rootValidator) {
		this.rootsService = rootsService;
		this.rootValidator = rootValidator;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("routes", rootsService.findAll());
		return "trams/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("route", rootsService.findOne(id));
		return "trams/show";
	}
	@GetMapping("/new")
	public String newRoot(@ModelAttribute("route") Route route) {
		
		return "trams/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("route") @Valid Route route, BindingResult bindingResult) {
		rootValidator.validate(route, bindingResult);
		if(bindingResult.hasErrors()) {
			return "trams/new";
		}
		
		rootsService.save(route);
		return "redirect:/trams";
	}
	
	@GetMapping("{id}/edit")
	public String edit(Model model, @PathVariable("id") int id){
		model.addAttribute("route", rootsService.findOne(id));
		return "trams/edit";
	}
	
	@PatchMapping("{id}")
	public String update(@ModelAttribute("route") @Valid Route route, BindingResult bindingResult,
						 @PathVariable("id") int id) {
		
		rootValidator.validate(route, bindingResult);
		if(bindingResult.hasErrors()) {
			return "trams/edit";
		}
		
		rootsService.update(id, route);
		return "redirect:/trams/{id}";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		rootsService.delete(id);
		
		return "redirect:/trams";
	}
}
