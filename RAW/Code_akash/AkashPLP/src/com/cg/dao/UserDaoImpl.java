package com.cg.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.entities.CandidatePersonal;
import com.cg.entities.CandidateQualifications;
import com.cg.entities.CandidateWorkHistory;
import com.cg.exception.RecruitmentException;

@Repository
@Transactional
public class UserDaoImpl implements IUserDao {


	@PersistenceContext
	EntityManager entityManager;
	
/*	@Override
	public List<JobRequirements> getJobs() throws RecruitmentException
	{
		TypedQuery<JobRequirements> query = entityManager.createQuery("SELECT jobs FROM JobRequirements jobs", JobRequirements.class);
		try
		{
			return query.getResultList();
		}
		catch(Exception e)
		{
			throw new RecruitmentException("Cant get jobs");
		}

	}

*/



	@Override
	public void candidPersonal(CandidatePersonal candPers) throws RecruitmentException {
		try
		{
			
			entityManager.persist(candPers);
			entityManager.flush();
		}
		catch(Exception e)
		{
			
			throw new RecruitmentException("Can't insert into candidate Personal");
		}
	}



	@Override
	public void candidQualification(CandidateQualifications candQual)
			throws RecruitmentException {
		System.out.println(candQual);
		try
		{
			entityManager.persist(candQual);
			entityManager.flush();

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
			entityManager.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RecruitmentException("cant insert into candidate Work History");
		}


	}
	
	@Override
	public CandidatePersonal getCandidatePersonalDetails(String candidateId) {
    TypedQuery<CandidatePersonal> query=entityManager.createQuery("select candidatePersonal From CandidatePersonal candidatePersonal where candidatePersonal.candidateId=:candID",CandidatePersonal.class);
		query.setParameter("candID", candidateId);
		CandidatePersonal candidate=query.getSingleResult();
		return candidate;
	}



	@Override
	public CandidatePersonal modifycandidPersonal(CandidatePersonal candidatePersonal) {
		return entityManager.merge(candidatePersonal);
		

	}



	@Override
	public CandidateQualifications getCandidateQualificationDetails(
			String candidateId) {
		 TypedQuery<CandidateQualifications> query=entityManager.createQuery("select candidateQualifications From CandidateQualifications candidateQualifications where candidateQualifications.candidateId=:candID",CandidateQualifications.class);
			query.setParameter("candID", candidateId);
			CandidateQualifications candidate=query.getSingleResult();
			return candidate;
	}



	@Override
	public CandidateQualifications modifycandidQualifications(
			CandidateQualifications candidateQualifications) {
		return entityManager.merge(candidateQualifications);
	}
	
	
/*
	@Override
	public List<JobRequirements> getJobByQual(String qual) {
    TypedQuery<JobRequirements> query=entityManager.createQuery("select jobRequirements From JobRequirements jobRequirements where jobRequirements.qualificationRequired=:pQual",JobRequirements.class);
		query.setParameter("pQual", qual);
		List<JobRequirements> accList=query.getResultList();
		return accList;
	}

	@Override
	public List<JobRequirements> getJobByPosition(String pos) {
		 TypedQuery<JobRequirements> query=entityManager.createQuery("select jobRequirements From JobRequirements jobRequirements where jobRequirements.positionRequired=:pPos",JobRequirements.class);
			query.setParameter("pPos", pos);
			List<JobRequirements> accList=query.getResultList();
			return accList;
	}

	@Override
	public List<JobRequirements> getJobByExperience(int exp) {
		 TypedQuery<JobRequirements> query=entityManager.createQuery("select jobRequirements From JobRequirements jobRequirements where jobRequirements.experienceRequired<=:pExp",JobRequirements.class);
			query.setParameter("pExp", exp);
			List<JobRequirements> accList=query.getResultList();
			return accList;
	}

	@Override
	public List<JobRequirements> getJobByLocation(String loc) {
		 TypedQuery<JobRequirements> query=entityManager.createQuery("select jobRequirements From JobRequirements jobRequirements where jobRequirements.jobLocation=:pLoc",JobRequirements.class);
			query.setParameter("pLoc", loc);
			List<JobRequirements> accList=query.getResultList();
			return accList;
	}
*/
}
