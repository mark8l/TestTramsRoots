package com.sharipov.test.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharipov.test.models.Route;
import com.sharipov.test.repositories.RouteRepository;

@Service
@Transactional(readOnly = true)
public class RoutesService {
	private final RouteRepository rootRepository;
	
	public RoutesService(RouteRepository rootRepository) {
		this.rootRepository = rootRepository;
	}
	
	public List<Route> findAll(){
		return rootRepository.findAll();
	}
	
	public Route findOne(int id) {
		Optional<Route> foundRoot = rootRepository.findById(id);
		
		return foundRoot.orElse(null);
	}
	
	@Transactional
	public void save(Route root) {
		rootRepository.save(root);
	}
	
	@Transactional
	public void update(int id, Route updatedRoot) {
		updatedRoot.setId(id);
		rootRepository.save(updatedRoot);
	}
	
	@Transactional
	public void delete(int id) {
		rootRepository.deleteById(id);;
	}
	
	public Optional<Route> findByCodeAndName(Route root){
		return rootRepository.findByCodeAndName(root.getCode(), root.getName());
	}
}
