package com.cg.recruitment.dao;

import java.util.List;

import com.cg.recruitment.entities.CandidatePersonal;
import com.cg.recruitment.entities.CandidateQualifications;
import com.cg.recruitment.entities.CandidateWorkHistory;
import com.cg.recruitment.entities.CompanyMaster;
import com.cg.recruitment.entities.HireDetails;
import com.cg.recruitment.entities.JobApplied;
import com.cg.recruitment.entities.JobRequirements;
import com.cg.recruitment.entities.Login;
import com.cg.recruitment.exception.RecruitmentException;

public interface IRecruitmentDAO {
	public void addCompanyDetails(CompanyMaster companyMaster);

	public void updateCompanyDetails(CompanyMaster companyMaster);

	public void postJobRequirements(JobRequirements jobRequirements);

	public CompanyMaster getCompany(String companyId);

	public abstract List<CandidateWorkHistory> getCandidateByQual(String qual);

	public abstract List<CandidateWorkHistory> getCandidateByPosition(String pos);

	public abstract List<CandidateWorkHistory> getCandidateByExperience(int exp);

	public abstract List<JobApplied> getAllAppliedCandidates();

	public void addHiredCandidates(HireDetails hiredetails);

	public abstract void signUp(Login loginSignup) throws RecruitmentException;

	public abstract Login getLoginDetails(String loginId);

	void beginTransaction();

	void commitTransaction() throws RecruitmentException;

	List<JobRequirements> getJobs() throws RecruitmentException;

	public abstract void candidPersonal(CandidatePersonal candpers)
			throws RecruitmentException;

	public abstract void candidQualification(CandidateQualifications candQual)
			throws RecruitmentException;

	public abstract void candidWorkHistory(CandidateWorkHistory candHist)
			throws RecruitmentException;

	public abstract List<JobRequirements> getJobByQual(String qual);

	public abstract List<JobRequirements> getJobByPosition(String pos);

	public abstract List<JobRequirements> getJobByExperience(int exp);

	public abstract List<JobRequirements> getJobByLocation(String loc);

	public abstract void insertApplyJob(JobApplied jobApplied)
			throws RecruitmentException;

}
