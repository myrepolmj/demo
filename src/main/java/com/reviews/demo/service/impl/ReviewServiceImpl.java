package com.reviews.demo.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reviews.demo.entity.ReviewDTO;
import com.reviews.demo.model.ReviewAvgModel;
import com.reviews.demo.model.ReviewModel;
import com.reviews.demo.repository.ReviewRepository;
import com.reviews.demo.service.ReviewService;
import com.reviews.demo.util.ReviewBuilder;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	ReviewBuilder reviewBuilder;
	@Value("${demo.sources}")
	private String configs;

	private static List<String> configArray;

	@PostConstruct
	public void onInit() {
		configArray = Arrays.asList(configs.split(","));
	}

	@Override
	public Integer addReview(ReviewModel reviewModel) {
		ReviewDTO entity = reviewBuilder.reviewEntityBuilder(reviewModel);
		reviewRepository.save(entity);
		return entity.getReviewId();
	}

	@Override
	public void save(List<ReviewDTO> reviewDTOs) {
		reviewRepository.saveAll(reviewDTOs);
	}

	@Override
	public Page<ReviewDTO> getReview(String date, String storeType, String rating, int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return reviewRepository.findByFilters(rating, storeType, paging);

	}

	@Override
	public Map<String, Map<String, String>> getTotalRatings() {
		Map<String, Map<String, String>> storeRatings = new HashMap<>();
		for (String reviewSource : configArray) {
			List<Object[]> ratings = reviewRepository.findRatings(reviewSource);
			Map<String, String> ratingCountMap = ratings.stream()
					.collect(Collectors.toMap(x -> x[0].toString(), x -> x[1].toString()));
			storeRatings.put(reviewSource, ratingCountMap);
		}

		return storeRatings;
	}

	@Override
	public Map<String, List<ReviewAvgModel>> findMonthlyAvgs() {
		Map<String, List<ReviewAvgModel>> storeRatings = new HashMap<>();

		for (String store : configArray) {
			List<Object[]> ratings = reviewRepository.findMonthlyAvgs(store);
			List<ReviewAvgModel> reviewAvgModels = ratings.stream().map(s -> reviewBuilder.reviewAverageModelBuilder(s))
					.collect(Collectors.toList());
			storeRatings.put(store, reviewAvgModels);

		}

		return storeRatings;
	}

}
