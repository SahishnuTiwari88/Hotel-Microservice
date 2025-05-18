package com.user.service.externalFeign.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.service.entities.Hotel;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelServices {
	//here we are going to write calling method but we don't have to provide it's implementation , b/c fien client will handle
	//implementation
	
	@GetMapping("hotel/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId);

}
