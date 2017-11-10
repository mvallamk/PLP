package com.cg.service;



import org.springframework.stereotype.Service;

import com.cg.entities.CandidatePersonal;
import com.cg.entities.CandidateQualifications;
import com.cg.entities.CandidateWorkHistory;
import com.cg.entities.Login;
import com.cg.exception.RecruitmentException;


public interface IServiceDao {

	
	public abstract void signUp(Login loginSignup) throws RecruitmentException;
	public abstract Login getLoginDetails(String loginId);
	boolean validateLoginDetails(String loginId, String password);
	
	public abstract void candidPersonal(CandidatePersonal candpers) throws RecruitmentException;
	public abstract void candidQualification(CandidateQualifications candQual) throws RecruitmentException;
	public abstract void candidWorkHistory(CandidateWorkHistory candHist) throws RecruitmentException;

}
