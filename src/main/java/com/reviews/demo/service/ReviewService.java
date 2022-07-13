package com.reviews.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.reviews.demo.entity.ReviewDTO;
import com.reviews.demo.model.ReviewAvgModel;
import com.reviews.demo.model.ReviewModel;

public interface ReviewService {

	Integer addReview(ReviewModel reviewModel);

	void save(List<ReviewDTO> reviewDTOs);

	Page<ReviewDTO> getReview(String date, String storeType, String rating, int page, int size);

	Map<String, Map<String, String>> getTotalRatings();

	Map<String, List<ReviewAvgModel>> findMonthlyAvgs();

}
