package com.user.service.services.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.externalFeign.services.HotelServices;
import com.user.service.repository.UserRepository;
import com.user.service.services.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired // for autowiring, rest templates bean should be available to spring container
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelServices hotelService; //from fien client interface
	//if we are going to call hotel service from this than we have to remove responseentity<Hotel> and logger info
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		//to generate unique id we use
		String randemUserId =  UUID.randomUUID().toString();
		user.setUserId(randemUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	//get single user based on id(but we need other information like rating related to the hotels) 
	@Override
	public User getUser(String userId) {
		//getting user data from database based on id
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User id is not found on server :"+userId));
		
		//now we have to fetch rating of the above user from Rating Service/rating database, so rating service should have
		//api so that we can call it here to get ratings of the user
		//we call rating service inside of our user service with the help of url
		//http:localhost:8082/ratings/user/userid i.e.
		//http://localhost:8093/ratings/user/7b851a38-ffe0-4a74-acf3-c542a0ff1a62
		//when one service call another service in it, it is done with the help of some http client
		//so user service should have some client with the help of which we can make contact by calling httpserver with help of 
		//httpapi, for that we use rest template
		
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);
		//will see by logging,check if we get data or not(print data)
		logger.info("{} ",ratingsOfUser);
		
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		
		//here we get all rating ,then 
		//new list with hotel details
		
		List<Rating> ratingList = ratings.stream().map(rating ->{ 
			//api call to hotel service to get the hotel
			//http://localhost:8092/hotel/9ae6c1a9-95eb-46f1-b86c-7dbba60f5cb7 (from hotel service based on hotel id)
			
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(),Hotel.class);
			//Hotel hotel = forEntity.getBody(); //using this we get hotel detail
			
			//using fien client HotelService interface get hotel detail
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			//logger.info("Response status code ",forEntity.getStatusCode());
			
			//set the hotel to rating
			rating.setHotel(hotel);
			
			//return the rating
			
			return rating;			
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);//setting rating called from rating service api in user service
		return user;
	}

}

//rest template is calling all the API's so we have to make corresponding changes in the rest template so that, it uses 
//service name instead of host and port number for that we use load balance
