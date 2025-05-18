package com.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.service.entities.Rating;
import com.user.service.externalFeign.services.RatingService;

@SpringBootTest
class MicroservicesApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;
	
//	@Test
//	public void addRating() {
//		Rating rating  = Rating.builder().rating(10).userId("").hotelId("").feedback("This is created using fiegn client").build();
//		Rating saveRating = ratingService.addRating(rating);
//		System.out.println("rating created");
//	}

}
//builder is used to create object directly