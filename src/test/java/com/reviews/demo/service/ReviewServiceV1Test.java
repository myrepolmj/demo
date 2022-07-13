package com.reviews.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.reviews.demo.entity.ReviewDTO;
import com.reviews.demo.model.ReviewModel;
import com.reviews.demo.repository.ReviewRepository;
import com.reviews.demo.service.impl.ReviewServiceImpl;
import com.reviews.demo.util.ReviewBuilder;

@RunWith(SpringRunner.class)
public class ReviewServiceV1Test {

	@InjectMocks
	ReviewServiceImpl reviewService;

	@Mock
	ReviewRepository repo;

	@Mock
	ReviewBuilder reviewBuilder;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void saveReviewTest() {
		ReviewModel reviewModel = new ReviewModel();
		reviewModel.setAuthor("Author");
		reviewModel.setProductName("amazon alexa");
		reviewModel.setRating("1");
		reviewModel.setReview("Good");
		reviewModel.setReviewedDate(new Date());
		reviewModel.setReviewSource("iTunes");
		ReviewDTO revEntity = new ReviewDTO();
		revEntity.setReviewId(1234567);

		when(reviewBuilder.reviewEntityBuilder(reviewModel)).thenReturn(revEntity);
		when(repo.save(revEntity)).thenReturn(revEntity);

		// test
		Integer addReview = reviewService.addReview(reviewModel);

		assertEquals(1234567, addReview);
		verify(repo, times(1)).save(revEntity);
	}

	@Test
	public void getReviewTest() {

		ReviewDTO r1 = new ReviewDTO();
		r1.setAuthor("author");
		r1.setProductName("Amazon Aexa");
		r1.setRating("1");
		r1.setReview("Review");
		r1.setReviewedDate(new Date());
		r1.setReviewId(12345);
		r1.setReviewSource("iTunes");
		r1.setTitle("Title");
		List<ReviewDTO> list = new ArrayList<>();
		list.add(r1);
		Page<ReviewDTO> pagedResponse = new PageImpl<>(list);

		String date = "2018-01-12T02:27:03.000Z";
		String rating = "1";
		String storeType = "iTunes";
		int page = 1;
		int size = 3;
		Pageable paging = PageRequest.of(page, size);
		when(repo.findByFilters(rating, storeType, paging)).thenReturn(pagedResponse);

		Page<ReviewDTO> addReview = reviewService.getReview(date, storeType, rating, page, size);

		assertEquals(pagedResponse, addReview);

	}

}