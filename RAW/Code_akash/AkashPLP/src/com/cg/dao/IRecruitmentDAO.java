package com.cg.dao;

import java.util.List;

import com.cg.entities.CandidatePersonal;
import com.cg.entities.CandidateQualifications;
import com.cg.entities.CandidateWorkHistory;
import com.cg.entities.CompWise;
import com.cg.entities.CompanyMaster;
import com.cg.entities.HireDetails;
import com.cg.entities.JobApplied;
import com.cg.entities.JobRequirements;
import com.cg.entities.JobWise;
import com.cg.entities.Login;
import com.cg.entities.MonthWise;
import com.cg.exception.RecruitmentException;

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
    List<JobRequirements>getJobs() throws RecruitmentException;
	
	public abstract void candidPersonal(CandidatePersonal candpers) throws RecruitmentException;
	public abstract void candidQualification(CandidateQualifications candQual) throws RecruitmentException;
	public abstract void candidWorkHistory(CandidateWorkHistory candHist) throws RecruitmentException;
	public abstract List<JobRequirements> getJobByQual(String qual);
	public abstract List<JobRequirements> getJobByPosition(String pos);
	public abstract List<JobRequirements> getJobByExperience(int exp);
	public abstract List<JobRequirements> getJobByLocation(String loc);
	
	
	public abstract void insertApplyJob(JobApplied jobApplied) throws RecruitmentException;

	
	
	public List<CompWise> companyWiseDetaisl();
	public List<JobWise> jobWiseDetaisl();
	public List<MonthWise> monthWiseDetaisl();
	
}
