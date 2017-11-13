package com.cg.recruitment.util;

import java.util.ArrayList;
import java.util.List;

public class Constant {


	public static List<String> getUsers()
	{
		List<String> users=new ArrayList<String>();
		users.add("User");
		users.add("Company");
		users.add("Admin");
		
		return users;
	}


	public static List<String> getQualifications() {
		List<String> qualifications = new ArrayList<String>();
		qualifications.add("HSC");
		qualifications.add("SSC");
		qualifications.add("BE");
		qualifications.add("Diploma");
		qualifications.add("PG");
		return qualifications;

	}
	
	public static List<String> getCities()
	{
		List<String> cities=new ArrayList<String>();
		cities.add("Pune");
		cities.add("Hyderabad");
		cities.add("Bangalore");
		cities.add("Mumbai");
		cities.add("Chennai");
		return cities;
		
	}

}
