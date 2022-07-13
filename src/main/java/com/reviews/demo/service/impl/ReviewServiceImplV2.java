package com.reviews.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.reviews.demo.entity.ReviewAvgDTO;
import com.reviews.demo.entity.ReviewRatingDTO;
import com.reviews.demo.model.ReviewAvgModel;
import com.reviews.demo.model.ReviewRatingModel;
import com.reviews.demo.repository.ReviewAvgRepository;
import com.reviews.demo.repository.ReviewRatingRepository;
import com.reviews.demo.service.ReviewServiceV2;
import com.reviews.demo.util.ReviewBuilder;

@Service
public class ReviewServiceImplV2 implements ReviewServiceV2 {

	@Autowired
	ReviewRatingRepository reviewRatingRepository;

	@Autowired
	ReviewAvgRepository reviewAvgRepository;

	@Autowired
	ReviewBuilder reviewBuilder;

	@Override
	public Map<String, List<ReviewRatingModel>> getTotalRatings(String reviewSource) {
		Map<String, List<ReviewRatingModel>> reviewRateModels = null;
		List<ReviewRatingDTO> ratings = null;
		if (reviewSource != null) {
			Optional<List<ReviewRatingDTO>> ratingsOpt = reviewRatingRepository
					.findByReviewSourceOrderByRatingAsc(reviewSource);
			if (ratingsOpt.isPresent()) {
				ratings = ratingsOpt.get();
			}
		} else {
			ratings = reviewRatingRepository.findAll(Sort.by(Sort.Direction.ASC, "rating"));
		}
		if (ratings != null) {
			List<ReviewRatingModel> reviewRatingModels = new ModelMapper().map(ratings,
					new TypeToken<List<ReviewRatingModel>>() {
					}.getType());
			reviewRateModels = reviewRatingModels.stream()
					.collect(Collectors.groupingBy(ReviewRatingModel::getReviewSource));
		}

		return reviewRateModels;
	}

	@Override
	public Map<String, List<ReviewAvgModel>> findMonthlyAvgs(String reviewSource) {
		Map<String, List<ReviewAvgModel>> storeRatings = new HashMap<>();
		List<ReviewAvgDTO> ratings = null;
		if (reviewSource != null) {
			Optional<List<ReviewAvgDTO>> opt = reviewAvgRepository
					.findByReviewSourceOrderByYearAscMonthAsc(reviewSource);
			if (opt.isPresent()) {
				List<ReviewAvgModel> reviewAvgModels = opt.get().stream()
						.map(s -> reviewBuilder.reviewAverageModelFromEntityBuilder(s)).collect(Collectors.toList());
				storeRatings.put(reviewSource, reviewAvgModels);
			}

		} else {
			ratings = reviewAvgRepository.findAll(Sort.by(Sort.Direction.ASC, "year", "month"));
			if (ratings != null) {
				storeRatings = ratings.stream().map(s -> reviewBuilder.reviewAverageModelFromEntityBuilder(s))
						.collect(Collectors.toList()).stream()
						.collect(Collectors.groupingBy(ReviewAvgModel::getReviewSource));
			}

		}
		return storeRatings;
	}

}
