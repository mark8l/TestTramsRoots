package com.sharipov.test.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharipov.test.models.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
	Optional<Route> findByCodeAndName(int code, String name);
}
