package com.rating.service.services;

import java.util.List;

import com.rating.service.entities.Rating;

public interface RatingService {
	
	//add
	Rating addRating(Rating rating);
	
//	//get rating by id
//	Rating getById(String ratingId);
	
	//get all rating
	List<Rating> getAllRating();
	
	//get all by userId
	
	List<Rating> getRatingByUserId(String userId);
	
	//get all by hotelId
	
	List<Rating> getRatingByHotelId(String hotelId);

}
