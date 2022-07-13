package com.reviews.demo.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.reviews.demo.entity.ReviewAvgDTO;
import com.reviews.demo.entity.ReviewConfigDTO;
import com.reviews.demo.entity.ReviewRatingDTO;
import com.reviews.demo.repository.ReviewAvgRepository;
import com.reviews.demo.repository.ReviewConfigRepository;
import com.reviews.demo.repository.ReviewRatingRepository;
import com.reviews.demo.repository.ReviewRepository;
import com.reviews.demo.service.ReviewServiceScheduler;
import com.reviews.demo.util.ReviewBuilder;

@Service
@ConditionalOnProperty(prefix = "schedule", name = "enabled", havingValue = "true")
public class ReviewSchedulerImpl implements ReviewServiceScheduler {

	@Autowired
	ReviewAvgRepository reviewAvgRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ReviewBuilder reviewBuilder;

	@Autowired
	ReviewRatingRepository reviewRatingRepository;

	@Autowired
	ReviewConfigRepository reviewConfigRepository;

	@Scheduled(fixedRate = 30000)
	@Transactional(rollbackOn = Exception.class)
	public void loadMonthlyAvg() {
		System.out.println("loadMonthlyAvg Method executed at every 30 seconds. Current time is :: " + new Date());
		
		List<ReviewAvgDTO> all = null;
		Optional<ReviewConfigDTO> optionalEntity = reviewConfigRepository.findById("1");
		ReviewConfigDTO entity = optionalEntity.get(); 

		List<Object[]> ratings2 = reviewRepository.findMonthlyAvgByReviewedDateAfter(entity.getLastRunTime());
		// review_source ,year(reviewed_date),month(reviewed_date),sum(rating),
		// count(rating)
		if (ratings2 != null) {
			all = reviewAvgRepository.findAll();
		}

		for (Object[] obj : ratings2) {
			boolean isFound = false;
			for (ReviewAvgDTO a : all) {
				if (((String) obj[0]).equals(a.getReviewSource())
						&& (((Integer) obj[1]).intValue() == a.getYear().intValue())
						&& (((Integer) obj[2]).intValue() == a.getMonth().intValue())) {
					a.setSum(a.getSum() + (Double) obj[3]);
					a.setTotalNum(a.getTotalNum().add((BigInteger) obj[4]));
					isFound = true;
					reviewAvgRepository.save(a);
					break;
				}
			}
			if (!isFound) {
				ReviewAvgDTO a = reviewBuilder.reviewAverageEntityBuilder(obj);
				reviewAvgRepository.save(a);
			}
		}
		entity.setLastRunTime(new Date());//Saves the time job is executed
		reviewConfigRepository.save(entity);

	}

	@Scheduled(fixedRate = 30000)
	@Transactional(rollbackOn = Exception.class)
	public void loadRatings() {
		System.out.println("loadRatings Method executed at every 30 seconds. Current time is :: " + new Date());
	
		List<ReviewRatingDTO> all = null;
		Optional<ReviewConfigDTO> optionalReviewConfigEntity = reviewConfigRepository.findById("2");
		ReviewConfigDTO reviewConfigEntity = optionalReviewConfigEntity.get();
		List<Object[]> ratings2 = reviewRepository.findAllByReviewedDateAfter(reviewConfigEntity.getLastRunTime());
		// review_source,rating,count(*)
		if (ratings2 != null) {
			all = reviewRatingRepository.findAll();
		}
		for (Object[] obj : ratings2) {
			boolean isFound = false;
			for (ReviewRatingDTO a : all) {
				if (((String) obj[0]).equals(a.getReviewSource()) && ((String) obj[1]).equals(a.getRating())) {
					a.setCount(a.getCount().add((BigInteger) obj[2]));
					isFound = true;
					reviewRatingRepository.save(a);
					break;
				}
			}
			if (!isFound) {
				ReviewRatingDTO a = reviewBuilder.reviewRatingEntityBuilder(obj);
				reviewRatingRepository.save(a);
			}
		}
		reviewConfigEntity.setLastRunTime(new Date());
		reviewConfigRepository.save(reviewConfigEntity);
	}

}
