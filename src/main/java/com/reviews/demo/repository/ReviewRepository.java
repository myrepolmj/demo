package com.reviews.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reviews.demo.entity.ReviewDTO;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewDTO, Integer> {

	Page<ReviewDTO> findByRating(String rating, Pageable pageable);

	Page<ReviewDTO> findByReviewSource(String store, Pageable paging);

	@Query(value = "select  rating,count(*) from reviews where review_source=:x group by rating order by rating asc", nativeQuery = true)
	List<Object[]> findRatings(@Param(value = "x") String x);

	@Query("SELECT c FROM ReviewEntity c WHERE (:rating is null or c.rating = :rating) and (:store is null"
			+ " or c.reviewSource = :store)")
	Page<ReviewDTO> findByFilters(@Param(value = "rating") String rating, @Param(value = "store") String store,
			Pageable paging);

	@Query(value = " select review_source, year(reviewed_date),month(reviewed_date),sum(rating), count(rating)\r\n"
			+ "      from reviews\r\n" + "      group by review_source, year(reviewed_date),month(reviewed_date)\r\n"
			+ "      order by review_source, year(reviewed_date),month(reviewed_date)", nativeQuery = true)
	List<Object[]> findMonthlyAvgs();

	@Query(value = " select  year(reviewed_date),month(reviewed_date),AVG(rating)\r\n"
			+ "      from reviews where review_source=:store\r\n"
			+ "      group by review_source, year(reviewed_date),month(reviewed_date)\r\n"
			+ "      order by review_source, year(reviewed_date),month(reviewed_date);", nativeQuery = true)
	List<Object[]> findMonthlyAvgs(@Param(value = "store") String store);

	@Query(value = "select  review_source,rating,count(*) from reviews  group by review_source,rating ", nativeQuery = true)
	List<Object[]> findRatings();
	
	@Query(value = "select  review_source,rating,count(*) from reviews where reviewed_date>= :date  group by review_source,rating ", nativeQuery = true)
	List<Object[]> findAllByReviewedDateAfter(@Param(value = "date")Date date);
	
	
	@Query(value = "select  review_source ,year(reviewed_date),month(reviewed_date),sum(rating), count(rating)\r\n"
			+ "      from reviews where reviewed_date>= :date  \r\n"
			+ "      group by review_source, year(reviewed_date),month(reviewed_date)\r\n"
			+ "      order by review_source, year(reviewed_date),month(reviewed_date) ", nativeQuery = true)
	List<Object[]> findMonthlyAvgByReviewedDateAfter(@Param(value = "date")Date date);

}
