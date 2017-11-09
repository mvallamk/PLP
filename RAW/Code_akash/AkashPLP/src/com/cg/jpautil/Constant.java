package com.cg.jpautil;

import java.util.ArrayList;
import java.util.List;

public class Constant {
	

	public static List<String> getTypeList()
	{
		ArrayList<String> typeList=new ArrayList<String>();
		typeList.add("User");
		typeList.add("Company");
		typeList.add("Admin");
		
		return typeList;
	}
	
	

}
