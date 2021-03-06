package com.cg.service;



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
	CandidatePersonal getCandidatePersonalDetails(String candidateId);

	public abstract CandidatePersonal modifycandidPersonal(CandidatePersonal candidatePersonal);
	public abstract CandidateQualifications getCandidateQualificationDetails(
			String candidateId);
	public abstract CandidateQualifications modifycandidQualifications(
			CandidateQualifications candidateQualifications);
}
