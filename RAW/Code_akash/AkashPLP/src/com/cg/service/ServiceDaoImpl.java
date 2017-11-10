package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ILoginDao;
import com.cg.dao.IUserDao;
import com.cg.entities.CandidatePersonal;
import com.cg.entities.CandidateQualifications;
import com.cg.entities.CandidateWorkHistory;
import com.cg.entities.Login;
import com.cg.exception.RecruitmentException;

@Service
public class ServiceDaoImpl implements IServiceDao {

	@Autowired
	ILoginDao loginDAO;
	
	@Autowired
	IUserDao userDAO;



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



	@Override
	public void candidPersonal(CandidatePersonal candPers)
			throws RecruitmentException {
		userDAO.candidPersonal(candPers);
	}



	@Override
	public void candidQualification(CandidateQualifications candQual)
			throws RecruitmentException {
		userDAO.candidQualification(candQual);
		
	}



	@Override
	public void candidWorkHistory(CandidateWorkHistory candHist)
			throws RecruitmentException {
		userDAO.candidWorkHistory(candHist);
		
	}


}
