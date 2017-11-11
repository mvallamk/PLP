package com.cg.recruitment.util;

import java.util.ArrayList;
import java.util.List;

public class Constant {

	public static List<String> getTypeList() {
		ArrayList<String> typeList = new ArrayList<String>();
		typeList.add("User");
		typeList.add("Company");
		typeList.add("Admin");

		return typeList;
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
