package com.reviews.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reviews.demo.entity.ReviewRatingDTO;
import com.reviews.demo.entity.ReviewRatingIdClass;

@Repository
public interface ReviewRatingRepository extends JpaRepository<ReviewRatingDTO, ReviewRatingIdClass> {

	Optional<List<ReviewRatingDTO>> findByReviewSourceOrderByRatingAsc(String store);
}
