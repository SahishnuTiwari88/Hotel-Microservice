package com.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
	//this class is used to store hotel details and Rating class has it's reference
	private String hotelId;
	private String hotelName;
	private String location;
	private String about;

}
