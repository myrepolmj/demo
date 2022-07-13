package com.reviews.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reviews.demo.entity.ReviewDTO;
import com.reviews.demo.model.ReviewAvgModel;
import com.reviews.demo.model.ReviewModel;
import com.reviews.demo.service.ReviewService;

@RestController
@RequestMapping(path = "/reviews/v1")
public class ReviewDemoController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping(path = "/review")
	public ResponseEntity<Integer> addReview(@RequestBody @Validated ReviewModel reviewModel) {
		return ResponseEntity.ok(reviewService.addReview(reviewModel));
	}

	@GetMapping(path = "/reviews")
	public ResponseEntity<Map<String, Object>> getReview(@RequestParam(required = false) String date,
			@RequestParam(required = false) String storeType, @RequestParam(required = false) String rating,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		Page<ReviewDTO> pages = reviewService.getReview(date, storeType, rating, page, size);
		Map<String, Object> response = new HashMap<>();
		response.put("result", pages);
		response.put("currentPage", pages.getNumber());
		response.put("totalItems", pages.getTotalElements());
		response.put("totalPages", pages.getTotalPages());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/total-ratings")
	public ResponseEntity<Map<String, Map<String, String>>> getTotalRatings() {
		return new ResponseEntity<>(reviewService.getTotalRatings(), HttpStatus.OK);
	}

	@GetMapping(path = "/monthly-ratings")
	public ResponseEntity<Map<String, List<ReviewAvgModel>>> findMonthlyAvgs() {
		return new ResponseEntity<>(reviewService.findMonthlyAvgs(), HttpStatus.OK);
	}
}
