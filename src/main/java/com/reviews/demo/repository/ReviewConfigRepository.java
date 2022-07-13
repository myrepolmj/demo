package com.reviews.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reviews.demo.entity.ReviewConfigDTO;

@Repository
public interface ReviewConfigRepository extends JpaRepository<ReviewConfigDTO, String> {
}
