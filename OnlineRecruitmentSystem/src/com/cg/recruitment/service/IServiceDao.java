package com.cg.recruitment.service;



import com.cg.recruitment.entities.Login;
import com.cg.recruitment.exception.RecruitmentException;


public interface IServiceDao {

	
	public abstract void signUp(Login loginSignup) throws RecruitmentException;
	public abstract Login getLoginDetails(String loginId);
	boolean validateLoginDetails(String loginId, String password);

}
