package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent.MethodType;
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
		model.addAttribute("qualifications", Constant.getQualifications());
		model.addAttribute("candQual", new CandidateQualifications());
		model.addAttribute("candPers", new CandidatePersonal());
		model.addAttribute("candWork", new CandidateWorkHistory());

		return "addresume";

	}

	@RequestMapping(value = "/addresume.htm")
	public String candidateAddResume(Model model,
			@ModelAttribute("candQual") CandidateQualifications candQual,
			@ModelAttribute("candPers") CandidatePersonal candPers,
			@ModelAttribute("candWork") CandidateWorkHistory candWork) {
		
		candPers.setCandidateId("Hello");
		candQual.setCandidate("Hello");
		candWork.setCandidateId("Hello");

		try {
			service.candidPersonal(candPers);
			service.candidQualification(candQual);
			service.candidWorkHistory(candWork);
		} 
		catch (RecruitmentException e) {
			model.addAttribute("error", e.getMessage());
		return "error";	
			
		}
		model.addAttribute("message", "Resume successfully added !!!");
		return "candidate";
	}
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
