package com.sharipov.test.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharipov.test.models.Root;

@Repository
public interface RootRepository extends JpaRepository<Root, Integer> {
	Optional<Root> findByCodeAndName(int code, String name);
}
