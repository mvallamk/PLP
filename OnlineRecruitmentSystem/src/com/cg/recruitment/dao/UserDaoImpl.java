package com.cg.recruitment.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.recruitment.entities.CandidatePersonal;
import com.cg.recruitment.entities.CandidateQualifications;
import com.cg.recruitment.entities.CandidateWorkHistory;
import com.cg.recruitment.exception.RecruitmentException;
import com.cg.recruitment.util.QueryMapper;

@Repository
@Transactional
public class UserDaoImpl implements IUserDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void candidPersonal(CandidatePersonal candPers)
			throws RecruitmentException {
		try {

			entityManager.persist(candPers);
			entityManager.flush();
		} catch (Exception e) {

			throw new RecruitmentException(
					"Can't insert into candidate Personal");
		}
	}

	@Override
	public void candidQualification(CandidateQualifications candQual)
			throws RecruitmentException {
		System.out.println(candQual);
		try {
			entityManager.persist(candQual);
			entityManager.flush();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RecruitmentException(
					"cant insert into candidate Qualification");
		}
	}

	@Override
	public void candidWorkHistory(CandidateWorkHistory candHist)
			throws RecruitmentException {
		try {
			entityManager.persist(candHist);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RecruitmentException(
					"cant insert into candidate Work History");
		}

	}

	@Override
	public CandidatePersonal getCandidatePersonalDetails(String candidateId) {
		TypedQuery<CandidatePersonal> query = entityManager
				.createQuery(QueryMapper.getCandidatePersonalDetailsQuery,
						CandidatePersonal.class);
		query.setParameter("candID", candidateId);
		CandidatePersonal candidate = query.getSingleResult();
		return candidate;
	}

	@Override
	public CandidatePersonal modifycandidPersonal(
			CandidatePersonal candidatePersonal) {
		return entityManager.merge(candidatePersonal);

	}

	@Override
	public CandidateQualifications getCandidateQualificationDetails(
			String candidateId) {
		TypedQuery<CandidateQualifications> query = entityManager
				.createQuery(
						"select candidateQualifications From CandidateQualifications candidateQualifications where candidateQualifications.candidateId=:candID",
						CandidateQualifications.class);
		query.setParameter("candID", candidateId);
		CandidateQualifications candidate = query.getSingleResult();
		return candidate;
	}

	@Override
	public CandidateQualifications modifycandidQualifications(
			CandidateQualifications candidateQualifications) {
		return entityManager.merge(candidateQualifications);
	}

	@Override
	public CandidateWorkHistory getCandidateWorkHistoryDetails(
			String candidateId) {

		TypedQuery<CandidateWorkHistory> query = entityManager
				.createQuery(
						"select candidateWorkHistory From CandidateWorkHistory candidateWorkHistory where candidateWorkHistory.candidateId=:candID",
						CandidateWorkHistory.class);
		query.setParameter("candID", candidateId);
		CandidateWorkHistory candidate = query.getSingleResult();

		return candidate;

	}

	@Override
	public CandidateWorkHistory modifycandidWorkHistory(
			CandidateWorkHistory candidateWorkHistory) {
		return entityManager.merge(candidateWorkHistory);
	}

}
