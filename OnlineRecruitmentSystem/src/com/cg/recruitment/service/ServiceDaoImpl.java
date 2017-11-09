package com.cg.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.recruitment.dao.ILoginDao;
import com.cg.recruitment.entities.Login;
import com.cg.recruitment.exception.RecruitmentException;

@Service
public class ServiceDaoImpl implements IServiceDao {

	@Autowired
	ILoginDao loginDAO;



	@Override
	public Login getLoginDetails(String loginId) {
		return loginDAO.getLoginDetails(loginId);
	}



	@Override
	public void signUp(Login loginSignup) throws RecruitmentException {
		loginDAO.signUp(loginSignup);
		
	}
	
	@Override
	public boolean validateLoginDetails(String loginId,String password) {
		
		
		if(loginDAO.getLoginDetails(loginId)==null)
			return false;
		else
		{
			Login loginDetails=loginDAO.getLoginDetails(loginId);
			if(loginDetails.getPassword().equals(password))
			{
				return true;
			}
			else
				return false;
		}
	}


}
