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

import com.sharipov.test.models.Root;
import com.sharipov.test.services.RootsService;
import com.sharipov.test.util.RootValidator;

@Controller
@RequestMapping("/trams")
public class RootsController {
	
	private final RootsService rootsService;
	private RootValidator rootValidator;
	
	public RootsController(RootsService rootsService, RootValidator rootValidator) {
		this.rootsService = rootsService;
		this.rootValidator = rootValidator;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("roots", rootsService.findAll());
		return "trams/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("root", rootsService.findOne(id));
		return "trams/show";
	}
	@GetMapping("/new")
	public String newRoot(@ModelAttribute("root") Root root) {
		
		return "trams/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("root") @Valid Root root, BindingResult bindingResult) {
		rootValidator.validate(root, bindingResult);
		if(bindingResult.hasErrors()) {
			return "trams/new";
		}
		
		rootsService.save(root);
		return "redirect:/trams";
	}
	
	@GetMapping("{id}/edit")
	public String edit(Model model, @PathVariable("id") int id){
		model.addAttribute("root", rootsService.findOne(id));
		return "trams/edit";
	}
	
	@PatchMapping("{id}")
	public String update(@ModelAttribute("root") @Valid Root root, BindingResult bindingResult,
						 @PathVariable("id") int id) {
		
		rootValidator.validate(root, bindingResult);
		if(bindingResult.hasErrors()) {
			return "trams/edit";
		}
		
		rootsService.update(id, root);
		return "redirect:/trams/{id}";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		rootsService.delete(id);
		
		return "redirect:/trams";
	}
}
