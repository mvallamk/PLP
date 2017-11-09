package com.cg.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

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
import com.cg.jpautil.JPAUtil;

public class RecruitmentDAOImpl implements IRecruitmentDAO {
	EntityManager entityManager=JPAUtil.getEntityManager();
	
	private static Logger logger = Logger.getLogger(com.cg.dao.RecruitmentDAOImpl.class);
	
	@Override
	public void addCompanyDetails(CompanyMaster companyMaster) {
		entityManager.persist(companyMaster);
		logger.info("Company details are added successfully");
	}

	@Override
	public void updateCompanyDetails(CompanyMaster companyMaster) {
		entityManager.merge(companyMaster);
		logger.info("Company details are updated successfully");
	}

	@Override
	public void postJobRequirements(JobRequirements jobRequirements) {
		entityManager.persist(jobRequirements);	
		logger.info("Job requirements are posted successfully");
	}
	
	@Override
	public CompanyMaster getCompany(String companyId) {
		logger.info("Method to retrieve company details");
		return entityManager.find(CompanyMaster.class, companyId);	
	}

	

	@Override
	public List<CandidateWorkHistory> getCandidateByQual(String qual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CandidateWorkHistory> getCandidateByPosition(String pos) {
		 TypedQuery<CandidateWorkHistory> query=entityManager.createQuery("select candidateWorkHistory From CandidateWorkHistory candidateWorkHistory  where candidateWorkHistory.positionHeld=:pPos",CandidateWorkHistory.class);
			query.setParameter("pPos", pos);
			List<CandidateWorkHistory> canList=query.getResultList();
			logger.info("Candidate list is retrieved successfully by position");
			return canList;
	}

	@Override
	public List<CandidateWorkHistory> getCandidateByExperience(int exp) {
		Query query=entityManager.createNativeQuery("select * from candidateworkhistory where (select (employmentto-employmentfrom)/365 from candidateworkhistory)>="+exp,CandidateWorkHistory.class);
		//query.setParameter("pCustomerName", customerName);
		List<CandidateWorkHistory> accList=query.getResultList();
		logger.info("Candidate list by experience is retrieved successfully");
		return accList;
	}

	@Override
	public List<JobApplied> getAllAppliedCandidates() {
		
			TypedQuery<JobApplied> query = entityManager.createQuery("SELECT jobApplied FROM JobApplied jobApplied ",JobApplied.class);
		@SuppressWarnings("unchecked")
		List<JobApplied> appliedCandidatesList = query.getResultList();
		logger.info("Candidate list of all candidates applied for the jobs is retrieved successfully");
		return appliedCandidatesList;
	}



	@Override
	public void addHiredCandidates(HireDetails hiredetails) {
		entityManager.persist(hiredetails);	
		logger.info("Candidate details of  hired candidates are inserted successfully");
	}
	

	@Override
	public void signUp(Login loginSignup) throws RecruitmentException {
		try
		{
		entityManager.persist(loginSignup);
		entityManager.getTransaction().commit();
		logger.info("User login credentials are inserted successfully");
		}
		catch(Exception e)
		{
			throw new RecruitmentException("UserId already taken. Try Another Id.");
		}
	}
	

	@Override
	public Login getLoginDetails(String loginId) {
		logger.info("Method to get the login credentials");
		return entityManager.find(Login.class, loginId);
	}
	
	@Override
	public List<JobRequirements> getJobs() throws RecruitmentException
	{
		TypedQuery<JobRequirements> query = entityManager.createQuery("SELECT jobs FROM JobRequirements jobs", JobRequirements.class);
		try
		{
			logger.info("Method to get the job list");
			return query.getResultList();
		}
		catch(Exception e)
		{
			logger.info("Job list retrieval failed");
			throw new RecruitmentException("Cant get jobs");
		}

	}

	@Override
	public void beginTransaction() 
	{
		entityManager.getTransaction().begin();
		logger.info("Transaction began successfully");
	}

	@Override
	public void commitTransaction() throws RecruitmentException 
	{
		try
		{
			entityManager.getTransaction().commit();
			logger.info("Transaction committed successfully");
		}
		catch(Exception e)
		{
			throw new RecruitmentException("Error while committing the Transaction");
		}

	}

	@Override
	public void candidPersonal(CandidatePersonal candpers) throws RecruitmentException {
		try
		{
			System.out.println(candpers);
			entityManager.persist(candpers);
			entityManager.getTransaction().commit();
			logger.info("Details of the candidate are inserted successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//throw new RecruitmentException("cant insert into candidate Personal");
		}
	}



	@Override
	public void candidQualification(CandidateQualifications candQual)
			throws RecruitmentException {
		System.out.println(candQual);
		try
		{
			entityManager.persist(candQual);
			entityManager.getTransaction().commit();
			logger.info("Candidate Qualification is inserted successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RecruitmentException("cant insert into candidate Qualification");
		}
	}

	@Override
	public void candidWorkHistory(CandidateWorkHistory candHist)
			throws RecruitmentException {
		try
		{
			entityManager.persist(candHist);
			entityManager.getTransaction().commit();
			logger.info("Candidate work history is inserted successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RecruitmentException("cant insert into candidate Work History");
		}


	}

	@Override
	public List<JobRequirements> getJobByQual(String qual) {
    TypedQuery<JobRequirements> query=entityManager.createQuery("select jobRequirements From JobRequirements jobRequirements where jobRequirements.qualificationRequired=:pQual",JobRequirements.class);
		query.setParameter("pQual", qual);
		List<JobRequirements> accList=query.getResultList();
		logger.info("Job list is retrieved successfully by qualification");
		return accList;
	}

	@Override
	public List<JobRequirements> getJobByPosition(String pos) {
		 TypedQuery<JobRequirements> query=entityManager.createQuery("select jobRequirements From JobRequirements jobRequirements where jobRequirements.positionRequired=:pPos",JobRequirements.class);
			query.setParameter("pPos", pos);
			List<JobRequirements> accList=query.getResultList();
			logger.info("Job list is retrieved successfully by position");
			return accList;
	}

	@Override
	public List<JobRequirements> getJobByExperience(int exp) {
		 TypedQuery<JobRequirements> query=entityManager.createQuery("select jobRequirements From JobRequirements jobRequirements where jobRequirements.experienceRequired<=:pExp",JobRequirements.class);
			query.setParameter("pExp", exp);
			List<JobRequirements> accList=query.getResultList();
			logger.info("Job list is retrieved successfully by experience");
			return accList;
	}

	@Override
	public List<JobRequirements> getJobByLocation(String loc) {
		 TypedQuery<JobRequirements> query=entityManager.createQuery("select jobRequirements From JobRequirements jobRequirements where jobRequirements.jobLocation=:pLoc",JobRequirements.class);
			query.setParameter("pLoc", loc);
			List<JobRequirements> accList=query.getResultList();
			logger.info("Job list is retrieved successfully by location");
			return accList;
	}

	@Override
	public void insertApplyJob(JobApplied jobApplied) throws RecruitmentException {
		beginTransaction();
		entityManager.persist(jobApplied);
		commitTransaction();
		logger.info("Job details for the applied jobs are inserted successfully");
	}
	
		@Override
	public List<CompWise> companyWiseDetaisl() {
		
		Query query=entityManager.createNativeQuery("select company_id,count(*) as no_of_placed From hire_details GROUP BY company_id",CompWise.class);
		logger.info("Reports generated on the basis of Company");
		return query.getResultList();
		
	}

	@Override
	public List<JobWise> jobWiseDetaisl() {
		Query query=entityManager.createNativeQuery("select job_id,count(*)as no_of_placed From  hire_details GROUP BY job_id",JobWise.class);
		logger.info("Reports generated on the basis of Job");
		return query.getResultList();
	}
	
	@Override
	public List<MonthWise> monthWiseDetaisl() {
		Query query=entityManager.createNativeQuery("select hire_date,candidate_id,company_id From  hire_details WHERE hire_date BETWEEN sysdate-30 AND sysdate",MonthWise.class);
		logger.info("Reports generated for previous month");
		return query.getResultList();
	}
	

}
