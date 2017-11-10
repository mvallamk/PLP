package com.cg.dao;

import java.util.List;

import com.cg.entities.CandidateWorkHistory;
import com.cg.entities.CompanyMaster;
import com.cg.entities.HireDetails;
import com.cg.entities.JobApplied;
import com.cg.entities.JobRequirements;

public interface ICompanyDao {
public void addCompanyDetails(CompanyMaster companyMaster);
public void updateCompanyDetails(CompanyMaster companyMaster);
public void postJobRequirements(JobRequirements jobRequirements);
public void beginTransaction();
public void commitTransaction();
public CompanyMaster getCompany(String companyId); 


public abstract List<CandidateWorkHistory> getCandidateByQual(String qual);
public abstract List<CandidateWorkHistory> getCandidateByPosition(String pos);
public abstract List<CandidateWorkHistory> getCandidateByExperience(int exp);
public abstract List<JobApplied> getAllAppliedCandidates();
public void addHiredCandidates(HireDetails hiredetails);
}
