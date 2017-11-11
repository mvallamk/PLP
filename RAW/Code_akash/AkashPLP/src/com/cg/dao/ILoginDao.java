package com.cg.dao;

import com.cg.entities.Login;
import com.cg.exception.RecruitmentException;

public interface ILoginDao {
	
	
	public abstract void signUp(Login loginSignup) throws RecruitmentException;
	public abstract Login getLoginDetails(String loginId);


}
