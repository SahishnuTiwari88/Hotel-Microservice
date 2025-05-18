package com.hotel.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.service.entities.Hotel;
import com.hotel.service.services.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel addHotel = hotelService.addHotel(hotel);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(addHotel);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> hotelById(@PathVariable("hotelId") String hotelId){
		Hotel hotel = hotelService.getHotelByid(hotelId);
		return ResponseEntity.ok(hotel);
		
	}
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel(){
		List<Hotel> allHotel = hotelService.getAllHotel();
		return ResponseEntity.ok(allHotel);
	}

}

//In order to call the various service with the common IP address/domain name with the help of load balancing we 
//can do it by service discovery
//service registry has all information like port number, status (UP,DOWN) of service and service name
//we can call services by there name from service registry
