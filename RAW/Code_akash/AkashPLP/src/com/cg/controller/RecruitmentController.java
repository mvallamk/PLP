package com.cg.controller;


import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.entities.CandidatePersonal;
import com.cg.entities.CandidateQualifications;
import com.cg.entities.CandidateWorkHistory;
import com.cg.entities.Login;
import com.cg.exception.RecruitmentException;
import com.cg.jpautil.Constant;
import com.cg.service.IServiceDao;

@Controller
public class RecruitmentController {

	final String LOGIN = "login";
	int status;
	// List<CandidateQualifications> qualifications = new
	// ArrayList<CandidateQualifications>();
	@Autowired
	IServiceDao service;

	@RequestMapping("/showsignupform.htm")
	public String showsignupPage(Model model) {

		/*
		 * Constant.cityList.add("Pune"); Constant.cityList.add("Mumbai");
		 * Constant.cityList.add("Bangalore");
		 * Constant.cityList.add("Hyderabad"); Constant.cityList.add("Chennai");
		 * Constant.cityList.add("Delhi"); Constant.cityList.add("Gurugram");
		 */

		model.addAttribute("typeList", Constant.getUsers());
		model.addAttribute(LOGIN, new Login());
		return "signup";
	}

	@RequestMapping(value = "/checksignup.htm", method = RequestMethod.POST)
	public String signup(Model model,
			@ModelAttribute(LOGIN) @Valid Login login, BindingResult result) {
		if (result.hasErrors()) {

			model.addAttribute("typeList", Constant.getUsers());
			return "signup";
		} else {
			try {
				service.signUp(login);
			} catch (RecruitmentException e) {

				return "error";
			}

			return "success";
		}
	}

	@RequestMapping("/showloginform.htm")
	public String showLoginPage(Model model) {

		model.addAttribute(LOGIN, new Login());
		return LOGIN;

	}

	/**
	 * 
	 * 
	 * @param model
	 * @param loginId
	 * @param password
	 * @return success
	 * @exception
	 */
	@RequestMapping(value = "/checklogin.htm", method = RequestMethod.POST)
	public String login(Model model, @RequestParam("loginId") String loginId,
			@RequestParam("password") String password) {
		Login login = new Login();
		login.setLoginId(loginId);
		login.setPassword(password);

		if (!service.validateLoginDetails(login.getLoginId(),
				login.getPassword())) {
			model.addAttribute("message", "Invalid Login Details");
			return LOGIN;
		} else {

			login.setType(service.getLoginDetails(login.getLoginId()).getType());
			model.addAttribute("loginId", login.getLoginId());

			if ("User".equals(login.getType()))
				return "candidate";
			else if ("Company".equals(login.getType()))
				return "company";
			else
				return "admin";
		}
	}

	@RequestMapping("/addresumeform.htm")
	public String candidateAddResumeForm(Model model) {
		// CandidateQualifications candQual = new CandidateQualifications();
		// model.addAttribute("qualifications", Constant.getQualifications());
		// model.addAttribute("candQual",null);
		// model.addAttribute("candPers",null);
		// model.addAttribute("candWork",null);

		return "addresume";

	}

	@RequestMapping("/addpersonalform.htm")
	public String addPersonalForm(Model model) {
		model.addAttribute("candPers", new CandidatePersonal());
		return "addresume";
	}

	@RequestMapping(value = "/savepersonal.htm", method = RequestMethod.POST)
	public String savePersonal(Model model,
			@ModelAttribute("candPers") CandidatePersonal candidatePersonal) {
		try {
			Date date2=candidatePersonal.getDob();
			Date date=new Date(date2.getYear(), date2.getMonth(), date2.getDate());
			candidatePersonal.setDob(date);
			
			candidatePersonal.setCandidateId("Bansal123");
			service.candidPersonal(candidatePersonal);
		} catch (RecruitmentException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
		
		
		model.addAttribute("candPers", null);
		return "addresume";
	}

	@RequestMapping("/addqualform.htm")
	public String addQualificationForm(Model model) {
		model.addAttribute("qualifications", Constant.getQualifications());
		model.addAttribute("candQual", new CandidateQualifications());
		return "addresume";
	}
	
	
	@RequestMapping(value = "/savequal.htm", method = RequestMethod.POST)
	public String savePersonal(Model model,
			@ModelAttribute("candQual") CandidateQualifications candidateQualifications) {
		try {
			//Date date2=candidatePersonal.getDob();
			//Date date=new Date(date2.getYear(), date2.getMonth(), date2.getDate());
			//candidatePersonal.setDob(date);
			
			candidateQualifications.setCandidateId("Bansal123");
			service.candidQualification(candidateQualifications);
		} catch (RecruitmentException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
		
		
		model.addAttribute("candQual", null);
		return "addresume";
	}
	
	@RequestMapping("/addworkform.htm")
	public String addWorkHistoryForm(Model model) {
		//model.addAttribute("qualifications", Constant.getQualifications());
		model.addAttribute("candWork", new CandidateWorkHistory());
		return "addresume";
	}
	
	
	@RequestMapping(value = "/saveworkhist.htm", method = RequestMethod.POST)
	public String saveWorkHistory(Model model,
			@ModelAttribute("candWork") CandidateWorkHistory candidateWorkHistory) {
		try {
			//Date date2=candidatePersonal.getDob();
			//Date date=new Date(date2.getYear(), date2.getMonth(), date2.getDate());
			//candidatePersonal.setDob(date);
			
			//candidateQualifications.setCandidate("heyyyy");
			Date date2=candidateWorkHistory.getEmploymentFrom();
			Date date1=candidateWorkHistory.getEmploymentTo();
			Date date=new Date(date2.getYear(), date2.getMonth(), date2.getDate());
			Date datee=new Date(date1.getYear(), date1.getMonth(), date1.getDate());
			candidateWorkHistory.setEmploymentFrom(date);
			candidateWorkHistory.setEmploymentTo(datee);
			service.candidWorkHistory(candidateWorkHistory);
		} catch (RecruitmentException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
		
		
		model.addAttribute("candQual", null);
		model.addAttribute("candWork", null);
		return "addresume";
	}
	
	@RequestMapping("/modifyresumeform.htm")
	public String candidateModifyResumeForm(Model model) {

		return "modifyresume";

	}
	
	
	@RequestMapping("/modifypersonalform.htm")
	public String modifyPersonalForm(Model model) {
		
		
		CandidatePersonal candidatePersonal=service.getCandidatePersonalDetails("Bansal123");
		model.addAttribute("candPers", candidatePersonal);
		
		return "modifyresume";
	}
	
	
	@RequestMapping(value = "/modifypersonal.htm", method = RequestMethod.POST)
	public String modifyPersonal(Model model,
			@ModelAttribute("candPers") CandidatePersonal candidatePersonal) throws RecruitmentException {
		Date date2=candidatePersonal.getDob();
		Date date=new Date(date2.getYear(), date2.getMonth(), date2.getDate());
		candidatePersonal.setDob(date);
		
		candidatePersonal.setCandidateId("Bansal123");
		service.modifycandidPersonal(candidatePersonal);
		
		
		model.addAttribute("candPers", null);
		return "modifyresume";
	}
	
	
	@RequestMapping("/modifyqualform.htm")
	public String modifyQualificationForm(Model model) {
		
		
		CandidateQualifications candidateQualification=service.getCandidateQualificationDetails("Bansal123");
		model.addAttribute("candQual", candidateQualification);
		
		return "modifyresume";
	}
	
	
	@RequestMapping(value = "/modifyqual.htm", method = RequestMethod.POST)
	public String modifyQualifications(Model model,
			@ModelAttribute("candQual") CandidateQualifications candidateQualifications) throws RecruitmentException {

		candidateQualifications.setCandidateId("Bansal123");
		service.modifycandidQualifications(candidateQualifications);
		
		
		model.addAttribute("candQual", null);
		return "modifyresume";
	}
	
	
	/*
	 * @RequestMapping(value = "/addresume.htm") public String
	 * candidateAddResume(Model model,
	 * 
	 * @ModelAttribute("candQual") CandidateQualifications candQual,
	 * 
	 * @ModelAttribute("candPers") CandidatePersonal candPers,
	 * 
	 * @ModelAttribute("candWork") CandidateWorkHistory candWork) {
	 * 
	 * candPers.setCandidateId("Hello"); candQual.setCandidate("Hello");
	 * candWork.setCandidateId("Hello");
	 * 
	 * try { service.candidPersonal(candPers);
	 * service.candidQualification(candQual);
	 * service.candidWorkHistory(candWork); } catch (RecruitmentException e) {
	 * model.addAttribute("error", e.getMessage()); return "error";
	 * 
	 * } model.addAttribute("message", "Resume successfully added !!!"); return
	 * "candidate"; }
	 */
	/*
	 * @RequestMapping("/addresumeform.htm") public String
	 * candidateAddResumeForm(Model model) {
	 * 
	 * status = 1; CandidateQualifications candQual1 = new
	 * CandidateQualifications(); CandidateQualifications candQual2 = null;
	 * CandidateQualifications candQual3 = null; CandidateQualifications
	 * candQual4 = null;
	 * 
	 * // candQual.setCollegeName("Your Name"); model.addAttribute("candPers",
	 * new CandidatePersonal()); model.addAttribute("candQual1", candQual1);
	 * model.addAttribute("candQual2", candQual2);
	 * model.addAttribute("candQual3", candQual3);
	 * model.addAttribute("candQual4", candQual4);
	 * model.addAttribute("candWork", new CandidateWorkHistory());
	 * 
	 * // qualList.add(c1);
	 * 
	 * // model.addAttribute("qualList", qualList); // qualList.add(candQual);
	 * 
	 * 
	 * 
	 * model.addAttribute("candPers",new CandidatePersonal());
	 * model.addAttribute("candQual1", candQual1);
	 * model.addAttribute("candQual2", candQual2);
	 * model.addAttribute("candQual3", candQual3);
	 * model.addAttribute("candQual4", candQual4);
	 * model.addAttribute("candWork", new CandidateWorkHistory());
	 * 
	 * return "addresume"; }
	 * 
	 * @RequestMapping("/addqual.htm") public String
	 * candidateAddResumeForm(Model model,
	 * 
	 * @ModelAttribute("candQual1") @Valid CandidateQualifications candQual1,
	 * 
	 * @ModelAttribute("candQual2") @Valid CandidateQualifications candQual2,
	 * 
	 * @ModelAttribute("candQual3") @Valid CandidateQualifications candQual3,
	 * 
	 * @ModelAttribute("candQual4") @Valid CandidateQualifications candQual4,
	 * 
	 * @ModelAttribute("candPers") @Valid CandidatePersonal candPers,
	 * 
	 * @ModelAttribute("candWork") @Valid CandidateWorkHistory candWork) {
	 * status++; if (status == 2) { candQual2 = new CandidateQualifications();
	 * model.addAttribute("candQual2", candQual2); candQual3 = null; candQual4 =
	 * null; } if (status == 3) { candQual3 = new CandidateQualifications();
	 * candQual4 = null; } if (status == 4) { candQual4 = new
	 * CandidateQualifications(); } model.addAttribute("qualifications",
	 * Constant.getQualName()); model.addAttribute("candPers", new
	 * CandidatePersonal()); model.addAttribute("candQual1", candQual1);
	 * model.addAttribute("candQual2", candQual2);
	 * model.addAttribute("candQual3", candQual3);
	 * model.addAttribute("candQual4", candQual4);
	 * model.addAttribute("candWork", new CandidateWorkHistory()); return
	 * "addresume"; }
	 * 
	 * @RequestMapping("/removequal.htm") public String
	 * candidateAddResumeFormRemove(Model model,
	 * 
	 * @ModelAttribute("candQual1") CandidateQualifications candQual1,
	 * 
	 * @ModelAttribute("candQual2") CandidateQualifications candQual2,
	 * 
	 * @ModelAttribute("candQual3") CandidateQualifications candQual3,
	 * 
	 * @ModelAttribute("candQual4") CandidateQualifications candQual4,
	 * 
	 * @ModelAttribute("candPers") CandidatePersonal candPers,
	 * 
	 * @ModelAttribute("candWork") CandidateWorkHistory candWork) { status--; if
	 * (status == 1) { candQual2 = null; candQual3 = null; candQual4 = null; }
	 * if (status == 2) { candQual3 = null; candQual4 = null; } if (status == 3)
	 * {
	 * 
	 * candQual4 = null; }
	 * 
	 * model.addAttribute("candPers", new CandidatePersonal());
	 * model.addAttribute("candQual1", candQual1);
	 * model.addAttribute("candQual2", candQual2);
	 * model.addAttribute("candQual3", candQual3);
	 * model.addAttribute("candQual4", candQual4);
	 * model.addAttribute("candWork", new CandidateWorkHistory()); return
	 * "addresume"; }
	 */

}
