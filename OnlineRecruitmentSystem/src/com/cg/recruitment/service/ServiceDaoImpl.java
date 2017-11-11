package com.cg.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.recruitment.dao.ILoginDao;
import com.cg.recruitment.dao.IUserDao;
import com.cg.recruitment.entities.CandidatePersonal;
import com.cg.recruitment.entities.CandidateQualifications;
import com.cg.recruitment.entities.CandidateWorkHistory;
import com.cg.recruitment.entities.Login;
import com.cg.recruitment.exception.RecruitmentException;

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
	public boolean validateLoginDetails(String loginId, String password) {

		if (loginDAO.getLoginDetails(loginId) == null)
			return false;
		else {
			Login loginDetails = loginDAO.getLoginDetails(loginId);
			if (loginDetails.getPassword().equals(password)) {
				return true;
			} else
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

	@Override
	public CandidatePersonal getCandidatePersonalDetails(String candidateId) {
		return userDAO.getCandidatePersonalDetails(candidateId);
	}

	@Override
	public CandidatePersonal modifycandidPersonal(
			CandidatePersonal candidatePersonal) {

		return userDAO.modifycandidPersonal(candidatePersonal);
	}

	@Override
	public CandidateQualifications getCandidateQualificationDetails(
			String candidateId) {

		return userDAO.getCandidateQualificationDetails(candidateId);
	}

	@Override
	public CandidateQualifications modifycandidQualifications(
			CandidateQualifications candidateQualifications) {

		return userDAO.modifycandidQualifications(candidateQualifications);

	}

	@Override
	public CandidateWorkHistory getCandidateWorkHistoryDetails(
			String candidateId) {

		return userDAO.getCandidateWorkHistoryDetails(candidateId);
	}

	@Override
	public CandidateWorkHistory modifycandidWorkHistory(
			CandidateWorkHistory candidateWorkHistory) {

		return userDAO.modifycandidWorkHistory(candidateWorkHistory);
	}

}
