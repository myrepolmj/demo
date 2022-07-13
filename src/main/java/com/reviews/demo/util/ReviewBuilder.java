package com.reviews.demo.util;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.reviews.demo.entity.ReviewAvgDTO;
import com.reviews.demo.entity.ReviewDTO;
import com.reviews.demo.entity.ReviewRatingDTO;
import com.reviews.demo.model.ReviewAvgModel;
import com.reviews.demo.model.ReviewModel;

@Component
public class ReviewBuilder {

	public ReviewDTO reviewEntityBuilder(ReviewModel reviewModel) {
		ReviewDTO revEntity = new ReviewDTO();
		revEntity.setAuthor(reviewModel.getAuthor());
		revEntity.setProductName(reviewModel.getProductName());
		revEntity.setRating(reviewModel.getRating());
		revEntity.setReview(reviewModel.getReview());
		revEntity.setReviewSource(reviewModel.getReviewSource());
		// revEntity.setReviewedDate(reviewModel.getReviewedDate());
		revEntity.setReviewedDate(new Date());// adding to make sure the date is current date.Ideally it should be from
												// Ui
		revEntity.setTitle(reviewModel.getTitle());

		return revEntity;
	}

	/**
	 * @param array
	 */
	public ReviewAvgModel reviewAverageModelBuilder(Object[] array) {
		ReviewAvgModel reviewAverageModel = new ReviewAvgModel();
		reviewAverageModel.setYear((Integer) array[0]);
		reviewAverageModel.setMonth((Integer) array[1]);
		reviewAverageModel.setRating((Double) array[2]);
		return reviewAverageModel;
	}

	public ReviewAvgDTO reviewAverageEntityBuilder(Object[] array) {
		ReviewAvgDTO reviewAverageEntity = new ReviewAvgDTO();
		reviewAverageEntity.setReviewSource((String) array[0]);
		reviewAverageEntity.setYear((Integer) array[1]);
		reviewAverageEntity.setMonth((Integer) array[2]);
		reviewAverageEntity.setSum((Double) array[3]);
		reviewAverageEntity.setTotalNum((BigInteger) array[4]);

		return reviewAverageEntity;
	}

	/**
	 * @param array
	 */
	public ReviewRatingDTO reviewRatingEntityBuilder(Object[] array) {
		ReviewRatingDTO reviewAverageEntity = new ReviewRatingDTO();
		reviewAverageEntity.setReviewSource((String) array[0]);
		reviewAverageEntity.setRating((String) array[1]);
		reviewAverageEntity.setCount((BigInteger) array[2]);

		return reviewAverageEntity;
	}

	/**
	 * @param array
	 */
	public ReviewAvgModel reviewAverageModelFromEntityBuilder(ReviewAvgDTO reviewAvgEntity) {
		ReviewAvgModel reviewAverageModel = new ReviewAvgModel();
		reviewAverageModel.setYear(reviewAvgEntity.getYear());
		reviewAverageModel.setMonth(reviewAvgEntity.getMonth());
		reviewAverageModel.setRating(reviewAvgEntity.getSum() / reviewAvgEntity.getTotalNum().doubleValue());
		reviewAverageModel.setReviewSource(reviewAvgEntity.getReviewSource());
		return reviewAverageModel;
	}
}
