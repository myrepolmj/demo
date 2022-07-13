package com.reviews.demo.service;

import java.util.List;
import java.util.Map;

import com.reviews.demo.model.ReviewAvgModel;
import com.reviews.demo.model.ReviewRatingModel;

public interface ReviewServiceV2 {

	Map<String, List<ReviewRatingModel>> getTotalRatings(String reviewSource);

	Map<String, List<ReviewAvgModel>> findMonthlyAvgs(String reviewSource);

}
