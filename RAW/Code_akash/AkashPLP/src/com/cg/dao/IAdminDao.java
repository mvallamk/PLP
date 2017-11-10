package com.cg.dao;

import java.util.List;

import com.cg.entities.HireDetails;

public interface IAdminDao {

	public List companyWiseDetaisl();
	public List<HireDetails> jobWiseDetaisl();
}
