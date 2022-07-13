package com.reviews.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reviews.demo.entity.ReviewAvgDTO;
import com.reviews.demo.entity.ReviewDTO;
import com.reviews.demo.entity.ReviewRatingDTO;
import com.reviews.demo.model.ReviewModel;
import com.reviews.demo.repository.ReviewAvgRepository;
import com.reviews.demo.repository.ReviewRatingRepository;
import com.reviews.demo.repository.ReviewRepository;
import com.reviews.demo.service.ReviewService;
import com.reviews.demo.util.ReviewBuilder;

@SpringBootApplication
@EnableScheduling
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(ReviewService reviewService, ReviewBuilder reviewBuilder ,
			ReviewRepository reviewRepository ,ReviewAvgRepository reviewAvgRepository ,
			ReviewRatingRepository reviewRatingRepository) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<ReviewModel>> typeReference = new TypeReference<List<ReviewModel>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/alexa.json");
			try {
				List<ReviewModel> reviews = mapper.readValue(inputStream, typeReference);
				List<ReviewDTO> entities = new ArrayList<>();
				reviews.stream().forEach(x -> entities.add(reviewBuilder.reviewEntityBuilder(x)));
				reviewService.save(entities);
				
			} catch (IOException e) {
				System.out.println("Unable to save reviews: " + e.getMessage());
			}

			List<Object[]> maonthlyAvgs = reviewRepository.findMonthlyAvgs();
			List<ReviewAvgDTO> ratingEntities = maonthlyAvgs.stream().map(s -> reviewBuilder.reviewAverageEntityBuilder(s))
					.collect(Collectors.toList());
			reviewAvgRepository.saveAll(ratingEntities);

			List<Object[]> reviewRatings = reviewRepository.findRatings();
			List<ReviewRatingDTO> reviewRatingEntities = reviewRatings.stream().map(s -> reviewBuilder.reviewRatingEntityBuilder(s))
					.collect(Collectors.toList());
			reviewRatingRepository.saveAll(reviewRatingEntities);
			
			System.out.println("One time Job completed!");

		};
	}

}
