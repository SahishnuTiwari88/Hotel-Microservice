package com.hotel.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Hotel {
	@Id
	private String hotelId;
	private String hotelName;
	private String location;
	private String about;

}
