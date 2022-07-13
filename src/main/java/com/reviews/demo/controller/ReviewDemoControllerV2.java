package com.reviews.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reviews.demo.model.ReviewAvgModel;
import com.reviews.demo.model.ReviewRatingModel;
import com.reviews.demo.service.ReviewServiceV2;

@RestController
@RequestMapping(path = "/reviews/v2")
public class ReviewDemoControllerV2 {

	@Autowired
	private ReviewServiceV2 reviewServiceV2;

	@GetMapping(path = "/total-ratings")
	public ResponseEntity<Map<String, List<ReviewRatingModel>>> getTotalRatings(@RequestParam(required = false) String reviewSource) {
		return new ResponseEntity<>(reviewServiceV2.getTotalRatings(reviewSource), HttpStatus.OK);
	}

	@GetMapping(path = "/monthly-ratings")
	public ResponseEntity<Map<String, List<ReviewAvgModel>>> findMonthlyAvgs(@RequestParam(required = false) String reviewSource) {
		return new ResponseEntity<>(reviewServiceV2.findMonthlyAvgs(reviewSource), HttpStatus.OK);
	}
}
