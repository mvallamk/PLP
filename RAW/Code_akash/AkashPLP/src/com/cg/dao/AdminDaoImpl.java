package com.cg.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import com.cg.entities.HireDetails;

import com.cg.jpautil.JPAUtil;

public class AdminDaoImpl implements IAdminDao {

	
	
	EntityManager entityManager=JPAUtil.getEntityManager();
	@Override
	public List<HireDetails> companyWiseDetaisl() {
		
		Query query=entityManager.createQuery("select count(*) From HireDetails hiredetails GROUP BY hireDetails.companyId");
		return query.getResultList();
		
	}

	@Override
	public List<HireDetails> jobWiseDetaisl() {
		Query query=entityManager.createQuery("select count(*) From HireDetails hiredetails GROUP BY hireDetails.jobId");
		return query.getResultList();
	}

}
