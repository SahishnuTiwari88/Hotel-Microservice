package com.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="micro_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	private String userId;//it should not be auto generated
	private String name;
	private String email;
	private String about;
	//adding rating list 
	@Transient  //indicates that it has not to be saved in database
	private List<Rating> ratings = new ArrayList<>();
	//wewill get rating from rating service i.e different service
}
