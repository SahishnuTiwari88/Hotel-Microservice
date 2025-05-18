package com.hotel.service.services;

import java.util.List;

import com.hotel.service.entities.Hotel;

public interface HotelService {
	
	//create 
	Hotel addHotel(Hotel hotel);
	
	
	//getall
	List<Hotel> getAllHotel();
	
	
	//get by id
	Hotel getHotelByid(String hotelId);

}
