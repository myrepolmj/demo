package com.reviews.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.reviews.demo.entity.ReviewAvgDTO;
import com.reviews.demo.entity.ReviewRatingDTO;
import com.reviews.demo.model.ReviewAvgModel;
import com.reviews.demo.model.ReviewRatingModel;
import com.reviews.demo.repository.ReviewAvgRepository;
import com.reviews.demo.repository.ReviewRatingRepository;
import com.reviews.demo.repository.ReviewRepository;
import com.reviews.demo.service.impl.ReviewServiceImplV2;
import com.reviews.demo.util.ReviewBuilder;

@RunWith(SpringRunner.class)
public class ReviewServiceV2Test {

	@InjectMocks
	ReviewServiceImplV2 reviewService;

	@Mock
	ReviewRepository repo;

	@Mock
	ReviewBuilder reviewBuilder;

	@Mock
	ReviewRatingRepository reviewRatingRepository;

	@Mock
	ReviewAvgRepository reviewAvgRepository;

	@BeforeEach
	public void init() throws NoSuchFieldException, SecurityException, Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void getReviewTestForAll() throws Exception {
		String configs = null;

		ReviewRatingDTO entity1 = new ReviewRatingDTO();
		entity1.setCount(new BigInteger("1"));
		entity1.setRating("2");
		entity1.setReviewSource("iTunes");
		ReviewRatingDTO entity2 = new ReviewRatingDTO();
		entity2.setCount(new BigInteger("1"));
		entity2.setRating("2");
		entity2.setReviewSource("GooglePlayStore");
		List<ReviewRatingDTO> lists = new ArrayList<>();
		lists.add(entity2);
		lists.add(entity1);
		when(reviewRatingRepository.findAll(Mockito.any(Sort.class))).thenReturn(lists);

		Map<String, List<ReviewRatingModel>> totalRatings = reviewService.getTotalRatings(configs);

		assertEquals(1, totalRatings.get("iTunes").size());
		assertEquals("2", totalRatings.get("iTunes").get(0).getRating());

	}

	@Test
	public void getReviewTestWithResourceType() throws Exception {
		String resourceType = "iTunes";

		ReviewRatingDTO entity1 = new ReviewRatingDTO();
		entity1.setCount(new BigInteger("1"));
		entity1.setRating("2");
		entity1.setReviewSource("iTunes");
		ReviewRatingDTO entity2 = new ReviewRatingDTO();
		entity2.setCount(new BigInteger("5"));
		entity2.setRating("5");
		entity2.setReviewSource("iTunes");
		List<ReviewRatingDTO> lists = new ArrayList<>();
		lists.add(entity1);
		lists.add(entity2);

		when(reviewRatingRepository.findByReviewSourceOrderByRatingAsc(resourceType)).thenReturn(Optional.of(lists));
		Map<String, List<ReviewRatingModel>> totalRatings = reviewService.getTotalRatings(resourceType);

		assertEquals(2, totalRatings.get("iTunes").size());
	}

	@Test
	public void getMonthlyAvgsResourceType() throws Exception {

		ReviewAvgDTO avgEntity = new ReviewAvgDTO();
		avgEntity.setMonth(2);
		avgEntity.setReviewSource("iTunes");
		avgEntity.setSum(new Double(48));
		avgEntity.setTotalNum(new BigInteger("12"));
		avgEntity.setYear(2018);

		List<ReviewAvgDTO> lists = new ArrayList<>();
		lists.add(avgEntity);

		ReviewAvgModel model = new ReviewAvgModel();
		model.setMonth(2);
		model.setReviewSource("iTunes");
		model.setRating(new Double(4));
		model.setYear(2018);

		when(reviewBuilder.reviewAverageModelFromEntityBuilder(avgEntity)).thenReturn(model);

		String resourceType = "iTunes";
		when(reviewAvgRepository.findByReviewSourceOrderByYearAscMonthAsc(resourceType)).thenReturn(Optional.of(lists));

		Map<String, List<ReviewAvgModel>> findMonthlyAvgs = reviewService.findMonthlyAvgs(resourceType);

		assertEquals(1, findMonthlyAvgs.get("iTunes").size());
		assertEquals(4, findMonthlyAvgs.get("iTunes").get(0).getRating());

	}

	@Test
	public void getMonthlyAvgsForAll() throws Exception {

		ReviewAvgDTO avgEntity = new ReviewAvgDTO();
		avgEntity.setMonth(2);
		avgEntity.setReviewSource("iTunes");
		avgEntity.setSum(new Double(48));
		avgEntity.setTotalNum(new BigInteger("12"));
		avgEntity.setYear(2018);

		ReviewAvgDTO avgEntity2 = new ReviewAvgDTO();
		avgEntity2.setMonth(2);
		avgEntity2.setReviewSource("GooglePlayStore");
		avgEntity2.setSum(new Double(75));
		avgEntity2.setTotalNum(new BigInteger("15"));
		avgEntity2.setYear(2017);

		List<ReviewAvgDTO> lists = new ArrayList<>();
		lists.add(avgEntity);
		lists.add(avgEntity2);

		ReviewAvgModel model = new ReviewAvgModel();
		model.setMonth(2);
		model.setReviewSource("iTunes");
		model.setRating(new Double(4));
		model.setYear(2018);

		ReviewAvgModel model2 = new ReviewAvgModel();
		model2.setMonth(2);
		model2.setReviewSource("GooglePlayStore");
		model2.setRating(new Double(4));
		model2.setYear(2017);

		when(reviewBuilder.reviewAverageModelFromEntityBuilder(avgEntity)).thenReturn(model);
		when(reviewBuilder.reviewAverageModelFromEntityBuilder(avgEntity2)).thenReturn(model);
		when(reviewAvgRepository.findAll(Mockito.any(Sort.class))).thenReturn(lists);

		Map<String, List<ReviewAvgModel>> findMonthlyAvgs = reviewService.findMonthlyAvgs(null);
		assertEquals(4, findMonthlyAvgs.get("iTunes").get(0).getRating());

	}

}