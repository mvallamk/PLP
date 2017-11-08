package com.cg.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.cg.entities.CandidateWorkHistory;
import com.cg.entities.CompanyMaster;
import com.cg.entities.HireDetails;
import com.cg.entities.JobApplied;
import com.cg.entities.JobRequirements;
import com.cg.jpautil.JPAUtil;

public class CompanyDaoImpl implements ICompanyDao {

	private EntityManager entityManager;
	
	public CompanyDaoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}
	
	private static Logger logger = Logger.getLogger(com.cg.dao.CompanyDaoImpl.class);
	
	@Override
	public void addCompanyDetails(CompanyMaster companyMaster) {
		entityManager.persist(companyMaster);	
		logger.info("Company details inserted successfully");
	}

	@Override
	public void updateCompanyDetails(CompanyMaster companyMaster) {
		entityManager.merge(companyMaster);
		logger.info("Company details updated successfully");
	}

	@Override
	public void postJobRequirements(JobRequirements jobRequirements) {
		entityManager.persist(jobRequirements);	
		logger.info("Job requirements posted successfully");
	}
	
	@Override
	public CompanyMaster getCompany(String companyId) {
		logger.info("Method to retrieve comppany details");
		return entityManager.find(CompanyMaster.class, companyId);		
	}

	@Override
	public void beginTransaction() {
		entityManager.getTransaction().begin();
		logger.info("Transaction began successfully");
	}

	@Override
	public void commitTransaction() {
		entityManager.getTransaction().commit();
		logger.info("Transaction commited successfully");
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
			logger.info("Candidate list by position is retrieved successfully");
			return canList;
	}

	@Override
	public List<CandidateWorkHistory> getCandidateByExperience(int exp) {
		Query query=entityManager.createNativeQuery("select * from candidateworkhistory where (select (employmentto-employmentfrom)/365 from candidateworkhistory)>="+exp,CandidateWorkHistory.class);
		
		List<CandidateWorkHistory> accList=query.getResultList();
		logger.info("Candidate list by experience is retrieved successfully");
		return accList;
	}

	@Override
	public List<JobApplied> getAllAppliedCandidates() {
		
		Query query = entityManager.createNamedQuery("SELECT * FROM jobApplied ",JobApplied.class);
		@SuppressWarnings("unchecked")
		List<JobApplied> appliedCandidatesList = query.getResultList();
		logger.info("Candidate list of all candidates applied for the jobs is retrieved successfully");
		return appliedCandidatesList;
	}



	@Override
	public void addHiredCandidates(HireDetails hiredetails) {
		entityManager.persist(hiredetails);	
		logger.info("Candidate list of  hired candidates is is inserted successfully");
	}

}
