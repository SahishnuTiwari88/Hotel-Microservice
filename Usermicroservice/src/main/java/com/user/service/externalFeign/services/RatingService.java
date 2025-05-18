package com.user.service.externalFeign.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.user.service.entities.Rating;

@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	//we can perform entire crud operation just by defining
	
	//post
	@PostMapping("/ratings")
	public Rating addRating( Rating rating);//it will call api and rating data pass to @RequestBody and saved
	
	//if we have not defined our method than we can pass data usingMap<String,Object>, but we have defined our own method 
	//so we just pass our data as rating which is given as json form
	
	
	//put
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating value);
	
	
	//delete
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable("ratingId")String ratingId);
}

//this is the declarative approach of the feign client where we just have to create interface of service and declare methods
//without giving implementation, as implementation done at run time  
