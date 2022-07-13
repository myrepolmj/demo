package com.reviews.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reviews.demo.entity.ReviewAvgDTO;
import com.reviews.demo.entity.ReviewAvgIdClass;

@Repository
public interface ReviewAvgRepository extends JpaRepository<ReviewAvgDTO, ReviewAvgIdClass> {

	Optional<List<ReviewAvgDTO>> findByReviewSourceOrderByYearAscMonthAsc(String store);

}
