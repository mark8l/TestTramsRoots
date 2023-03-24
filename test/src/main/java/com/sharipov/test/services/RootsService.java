package com.sharipov.test.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharipov.test.models.Root;
import com.sharipov.test.repositories.RootRepository;

@Service
@Transactional(readOnly = true)
public class RootsService {
	private final RootRepository rootRepository;
	
	public RootsService(RootRepository rootRepository) {
		this.rootRepository = rootRepository;
	}
	
	public List<Root> findAll(){
		return rootRepository.findAll();
	}
	
	public Root findOne(int id) {
		Optional<Root> foundRoot = rootRepository.findById(id);
		
		return foundRoot.orElse(null);
	}
	
	@Transactional
	public void save(Root root) {
		rootRepository.save(root);
	}
	
	@Transactional
	public void update(int id, Root updatedRoot) {
		updatedRoot.setId(id);
		rootRepository.save(updatedRoot);
	}
	
	@Transactional
	public void delete(int id) {
		rootRepository.deleteById(id);;
	}
	
	public Optional<Root> findByCodeAndName(Root root){
		return rootRepository.findByCodeAndName(root.getCode(), root.getName());
	}
}
