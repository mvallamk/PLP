package com.cg.dao;

import com.cg.entities.CandidatePersonal;
import com.cg.entities.CandidateQualifications;
import com.cg.entities.CandidateWorkHistory;
import com.cg.exception.RecruitmentException;



public interface IUserDao 
{
	//List<JobRequirements>getJobs() throws RecruitmentException;

	public abstract void candidPersonal(CandidatePersonal candPers) throws RecruitmentException;
	public abstract void candidQualification(CandidateQualifications candQual) throws RecruitmentException;
	public abstract void candidWorkHistory(CandidateWorkHistory candHist) throws RecruitmentException;
	/*public abstract List<JobRequirements> getJobByQual(String qual);
	public abstract List<JobRequirements> getJobByPosition(String pos);
	public abstract List<JobRequirements> getJobByExperience(int exp);
	public abstract List<JobRequirements> getJobByLocation(String loc);
*/
}
