package com.rating.service.services.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.service.entities.Rating;
import com.rating.service.repository.RatingRepository;
import com.rating.service.services.RatingService;
@Service
public class RatingServiceImpl implements RatingService{
	@Autowired
	private RatingRepository ratingReposity;

	@Override
	public Rating addRating(Rating rating) {
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return ratingReposity.save(rating);
	}


	@Override
	public List<Rating> getAllRating() {
		
		return ratingReposity.findAll();
	}




	@Override
	public List<Rating> getRatingByUserId(String userId) {
		
		return ratingReposity.findByUserId(userId);
	}


	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		
		return ratingReposity.findByHotelId(hotelId);
	}

}
