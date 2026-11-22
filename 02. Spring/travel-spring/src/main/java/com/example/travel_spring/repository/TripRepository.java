
package com.example.travel_spring.repository;

import com.example.travel_spring.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
