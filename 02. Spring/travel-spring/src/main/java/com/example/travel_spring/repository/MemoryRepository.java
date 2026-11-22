
package com.example.travel_spring.repository;

import com.example.travel_spring.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Integer> {
}
