package com.cg.recruitment.util;

import java.util.ArrayList;
import java.util.List;

public class Constant {

<<<<<<< HEAD
	public static List<String> getUsers()
	{
		List<String> users=new ArrayList<String>();
		users.add("User");
		users.add("Company");
		users.add("Admin");
		
		return users;
=======
	public static List<String> getTypeList() {
		ArrayList<String> typeList = new ArrayList<String>();
		typeList.add("User");
		typeList.add("Company");
		typeList.add("Admin");

		return typeList;
>>>>>>> f76d0a244066984b3eea0b7f4c309393a7fb0573
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

}
