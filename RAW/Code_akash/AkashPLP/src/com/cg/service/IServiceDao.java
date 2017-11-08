package com.cg.service;



import org.springframework.stereotype.Service;

import com.cg.entities.Login;
import com.cg.exception.RecruitmentException;


public interface IServiceDao {

	
	public abstract void signUp(Login loginSignup) throws RecruitmentException;
	public abstract Login getLoginDetails(String loginId);
	boolean validateLoginDetails(String loginId, String password);

}
