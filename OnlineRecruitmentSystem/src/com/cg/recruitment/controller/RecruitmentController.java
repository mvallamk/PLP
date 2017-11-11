package com.cg.recruitment.controller;

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

import com.cg.recruitment.entities.CandidatePersonal;
import com.cg.recruitment.entities.CandidateQualifications;
import com.cg.recruitment.entities.CandidateWorkHistory;
import com.cg.recruitment.entities.Login;
import com.cg.recruitment.exception.RecruitmentException;
import com.cg.recruitment.service.IServiceDao;
import com.cg.recruitment.util.Constant;

@Controller
public class RecruitmentController {

	final String LOGIN = "login";
	int status;
	String candidateId = null;

	@Autowired
	IServiceDao service;

	@RequestMapping("/showloginform.htm")
	public String showLoginPage(Model model) {

		model.addAttribute(LOGIN, new Login());
		return LOGIN;

	}

	/**
	 * Fetching the entered login-Id and Password from login.jsp and validating
	 * the login credentials by checking its availability in database and
	 * redirecting the program to appropriate jsp page depending upon the type
	 * of user
	 * 
	 * @param model
	 * @param loginId
	 * @param password
	 * @return success.jsp
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

			if ("User".equals(login.getType())) {
				candidateId = login.getLoginId();
				return "candidate";
			}

			else if ("Company".equals(login.getType()))
				return "company";
			else
				return "admin";
		}
	}

	/**
	 * Sending the list of type of users to signup.jsp
	 * 
	 * @param model
	 * @return signup.jsp
	 */
	@RequestMapping("/showsignupform.htm")
	public String showsignupPage(Model model) {

		model.addAttribute("typeList", Constant.getTypeList());
		model.addAttribute(LOGIN, new Login());
		return "signup";
	}

	/**
	 * Fetching the signup credentials from signup.jsp and inserting them into
	 * database
	 * 
	 * @param model
	 * @param login
	 * @param result
	 * @exception RecruitmentException
	 * @return success.jsp/error.jsp
	 */
	@RequestMapping(value = "/checksignup.htm", method = RequestMethod.POST)
	public String signup(Model model,
			@ModelAttribute(LOGIN) @Valid Login login, BindingResult result) {
		if (result.hasErrors()) {

			model.addAttribute("typeList", Constant.getTypeList());
			return "signup";
		} else {
			try {
				service.signUp(login);
			} catch (RecruitmentException e) {

				return "error";
			}

			return "login";
		}
	}

	/**
	 * When the user selects add resume from candidate.jsp the page is
	 * redirected to addresume.jsp
	 * 
	 * @param model
	 * @return addresume.jsp
	 */
	@RequestMapping("/addresumeform.htm")
	public String candidateAddResumeForm(Model model) {
		return "addresume";

	}

	/**
	 * When the user clicks on add Personal details in addresume.jsp he is
	 * redirected here and an object of personal details is sent.
	 * 
	 * @param model
	 * @return addresume.jsp
	 */
	@RequestMapping("/addpersonalform.htm")
	public String addPersonalForm(Model model) {
		model.addAttribute("candPers", new CandidatePersonal());
		return "addresume";
	}

	/**
	 * Onclicking save personal details in addresume.jsp the page is directed
	 * here and the personal details are saved and page is sent to addresume.jsp
	 * 
	 * @param model
	 * @param candidatePersonal
	 * @exception RecruitmentException
	 * @return addresume.jsp
	 */
	@RequestMapping(value = "/savepersonal.htm", method = RequestMethod.POST)
	public String savePersonal(Model model,
			@ModelAttribute("candPers") CandidatePersonal candidatePersonal) {
		try {
			Date dateFromPage = candidatePersonal.getDob();
			Date dateOfBirth = new Date(dateFromPage.getYear(),
					dateFromPage.getMonth(), dateFromPage.getDate());
			candidatePersonal.setDob(dateOfBirth);

			candidatePersonal.setCandidateId(candidateId);
			service.candidPersonal(candidatePersonal);
		} catch (RecruitmentException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}

		model.addAttribute("candPers", null);
		return "addresume";
	}

	/**
	 * On clicking add Qualificationdetails in addresume.jsp the page is
	 * directed here and the personal details are saved and now to add
	 * qualifications an object of candidateQualifications is sent to
	 * addresume.jsp
	 * 
	 * @param model
	 * @return addresume.jsp
	 */
	@RequestMapping("/addqualform.htm")
	public String addQualificationForm(Model model) {
		model.addAttribute("qualifications", Constant.getQualifications());
		model.addAttribute("candQual", new CandidateQualifications());
		return "addresume";
	}

	/**
	 * Onclicking save QualificationDetails details in addresume.jsp the page is
	 * directed here and the Qualification details are saved and page is
	 * directed to addresume.jsp
	 * 
	 * @param model
	 * @exception RecruitmentException
	 * @return addresume.jsp
	 */
	@RequestMapping(value = "/savequal.htm", method = RequestMethod.POST)
	public String savePersonal(
			Model model,
			@ModelAttribute("candQual") CandidateQualifications candidateQualifications) {
		try {

			candidateQualifications.setCandidateId(candidateId);
			service.candidQualification(candidateQualifications);
		} catch (RecruitmentException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}

		model.addAttribute("candQual", null);
		return "addresume";
	}

	/**
	 * On clicking add workHistory in addresume.jsp the page is directed here
	 * and and to add work history an object of candidateWorkHistory is sent to
	 * addresume.jsp
	 * 
	 * @param model
	 * @return addresume.jsp
	 */
	@RequestMapping("/addworkform.htm")
	public String addWorkHistoryForm(Model model) {

		model.addAttribute("candWork", new CandidateWorkHistory());
		return "addresume";
	}

	/**
	 * On clicking save work history in addresume.jsp the page is directed here
	 * and the work history is saved.
	 * 
	 * @param model
	 * @param candidateWorkHistory
	 * @exception RecruitmentException
	 * @return addresume.jsp
	 */
	@RequestMapping(value = "/saveworkhist.htm", method = RequestMethod.POST)
	public String saveWorkHistory(
			Model model,
			@ModelAttribute("candWork") CandidateWorkHistory candidateWorkHistory) {
		try {

			Date date2 = candidateWorkHistory.getEmploymentFrom();
			Date date1 = candidateWorkHistory.getEmploymentTo();
			@SuppressWarnings("deprecation")
			Date employmentFrom = new Date(date2.getYear(), date2.getMonth(),
					date2.getDate());
			@SuppressWarnings("deprecation")
			Date employmentTo = new Date(date1.getYear(), date1.getMonth(),
					date1.getDate());
			candidateWorkHistory.setEmploymentFrom(employmentFrom);
			candidateWorkHistory.setEmploymentTo(employmentTo);
			candidateWorkHistory.setCandidateId(candidateId);
			service.candidWorkHistory(candidateWorkHistory);
		} catch (RecruitmentException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}

		model.addAttribute("candQual", null);
		model.addAttribute("candWork", null);
		return "addresume";
	}

	/**
	 * When selected modify resume in candidate.jsp page is directed here
	 * 
	 * @param model
	 * @return modifyresume.jsp
	 */
	@RequestMapping("/modifyresumeform.htm")
	public String candidateModifyResumeForm(Model model) {

		return "modifyresume";

	}

	/**
	 * When modify personal details is selected the the page is directed here
	 * and the personal details are fetched from data base and directed to
	 * modifyresume.jsp
	 *
	 * @param model
	 * @return modifyresume.jsp
	 */
	@RequestMapping("/modifypersonalform.htm")
	public String modifyPersonalForm(Model model) {

		CandidatePersonal candidatePersonal = service
				.getCandidatePersonalDetails(candidateId);
		model.addAttribute("candPers", candidatePersonal);

		return "modifyresume";
	}

	/**
	 * When the personal details are updated and saved the page is directed here
	 * and the modified details are saved and is directed to modifyresume.jsp
	 * 
	 * @param model
	 * @param candidatePersonal
	 * @return modifyresume.jsp
	 * @throws RecruitmentException
	 */
	@RequestMapping(value = "/modifypersonal.htm", method = RequestMethod.POST)
	public String modifyPersonal(Model model,
			@ModelAttribute("candPers") CandidatePersonal candidatePersonal)
			throws RecruitmentException {
		Date date2 = candidatePersonal.getDob();
		Date date = new Date(date2.getYear(), date2.getMonth(), date2.getDate());
		candidatePersonal.setDob(date);

		candidatePersonal.setCandidateId(candidateId);
		service.modifycandidPersonal(candidatePersonal);

		model.addAttribute("candPers", null);
		return "modifyresume";
	}

	/**
	 * When modify Qualification details is selected then the page is directed
	 * here and the Qualification details are fetched from data base and
	 * directed to modifyresume.jsp
	 * 
	 * @param model
	 * @return modifyresume.jsp
	 */
	@RequestMapping("/modifyqualform.htm")
	public String modifyQualificationForm(Model model) {

		CandidateQualifications candidateQualification = service
				.getCandidateQualificationDetails("candidateId");
		model.addAttribute("candQual", candidateQualification);

		return "modifyresume";
	}

	/**
	 * When the Qualification details are updated and saved the page is directed
	 * here and the modified details are saved and is directed to
	 * modifyresume.jsp
	 * 
	 * @param model
	 * @param candidateQualifications
	 * @return modifyresume.jsp
	 * @throws RecruitmentException
	 */
	@RequestMapping(value = "/modifyqual.htm", method = RequestMethod.POST)
	public String modifyQualifications(
			Model model,
			@ModelAttribute("candQual") CandidateQualifications candidateQualifications)
			throws RecruitmentException {

		candidateQualifications.setCandidateId(candidateId);
		service.modifycandidQualifications(candidateQualifications);

		model.addAttribute("candQual", null);
		return "modifyresume";
	}

	/**
	 * When modify Work history is selected then the page is directed here and
	 * the Work history details are fetched from data base and directed to
	 * modifyresume.jsp
	 * 
	 * @param model
	 * @return modifyresume.jsp
	 */
	@RequestMapping("/modifyworkhistform.htm")
	public String modifyWorkHistoryForm(Model model) {

		CandidateWorkHistory candidateWorkHistory = service
				.getCandidateWorkHistoryDetails(candidateId);
		model.addAttribute("candWork", candidateWorkHistory);

		return "modifyresume";
	}

	/**
	 * When the Work History details are updated and saved the page is directed
	 * here and the modified details are saved and is directed to
	 * modifyresume.jsp
	 * 
	 * @param model
	 * @param candidateWorkHistory
	 * @return modifyresume.jsp
	 * @throws RecruitmentException
	 */
	@RequestMapping(value = "/modifyworkhist.htm", method = RequestMethod.POST)
	public String modifyQualifications(
			Model model,
			@ModelAttribute("candWork") CandidateWorkHistory candidateWorkHistory)
			throws RecruitmentException {

		Date date2 = candidateWorkHistory.getEmploymentFrom();
		Date date1 = candidateWorkHistory.getEmploymentTo();
		@SuppressWarnings("deprecation")
		Date date = new Date(date2.getYear(), date2.getMonth(), date2.getDate());
		@SuppressWarnings("deprecation")
		Date datee = new Date(date1.getYear(), date1.getMonth(),
				date1.getDate());
		candidateWorkHistory.setEmploymentFrom(date);
		candidateWorkHistory.setEmploymentTo(datee);
		candidateWorkHistory.setCandidateId(candidateId);
		service.modifycandidWorkHistory(candidateWorkHistory);

		model.addAttribute("candWork", null);
		return "modifyresume";
	}

}
