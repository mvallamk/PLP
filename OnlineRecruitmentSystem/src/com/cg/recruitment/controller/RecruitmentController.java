package com.cg.recruitment.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
import com.cg.recruitment.entities.JobApplied;
import com.cg.recruitment.entities.JobRequirements;
import com.cg.recruitment.entities.Login;
import com.cg.recruitment.exception.RecruitmentException;
import com.cg.recruitment.service.IRecruitmentService;
import com.cg.recruitment.util.Constant;
import com.cg.recruitment.util.DateUtility;

@Controller
public class RecruitmentController {

	final String LOGIN = "login";
	String candidateId = null;

	@Autowired
	IRecruitmentService service;

	/**
	 * From home page when you click on log in user is directed here.
	 * 
	 * @param model
	 * @return LOGIN.html
	 */
	@RequestMapping("/showloginform.htm")
	public String showLoginPage(Model model) {

		model.addAttribute(LOGIN, new Login());
		return LOGIN;

	}

	/**
	 * When ever user selects Sign out he will be directed here
	 * 
	 * @param model
	 * @return LOGIN.html
	 */
	@RequestMapping("/signout.htm")
	public String signOut(Model model) {
		Login login = new Login();
		login.setLoginId(candidateId);
		candidateId = null;
		model.addAttribute(LOGIN, login);
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
	 * @throws RecruitmentException
	 */
	@RequestMapping(value = "/checklogin.htm", method = RequestMethod.POST)
	public String login(Model model, @RequestParam("loginId") String loginId,
			@RequestParam("password") String password)
			throws RecruitmentException {
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

		model.addAttribute("typeList", Constant.getUsers());
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

			model.addAttribute("typeList", Constant.getUsers());
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
		model.addAttribute("currentDate", Date.valueOf(LocalDate.now()));
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
			candidatePersonal.setDob(DateUtility.parseDate(dateFromPage));

			candidatePersonal.setCandidateId(candidateId);
			service.addCandidatePersonalDetails(candidatePersonal);
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
		model.addAttribute("currentYear", LocalDate.now().getYear());
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
			service.addCandidateQualificationDetails(candidateQualifications);
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
		model.addAttribute("currentDate", Date.valueOf(LocalDate.now()));
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

			Date employmentFrom = candidateWorkHistory.getEmploymentFrom();
			Date employmentTo = candidateWorkHistory.getEmploymentTo();

			candidateWorkHistory.setEmploymentFrom(DateUtility
					.parseDate(employmentFrom));
			candidateWorkHistory.setEmploymentTo(DateUtility
					.parseDate(employmentTo));
			candidateWorkHistory.setCandidateId(candidateId);
			service.addCandidateWorkHistoryDetails(candidateWorkHistory);
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

	@RequestMapping("/backtocandidate.htm")
	public String backToCandidate(Model model) {

		return "candidate";
	}

	/**
	 * When modify personal details is selected the the page is directed here
	 * and the personal details are fetched from data base and directed to
	 * modifyresume.jsp
	 *
	 * @param model
	 * @return modifyresume.jsp
	 * @throws RecruitmentException
	 */
	@RequestMapping("/modifypersonalform.htm")
	public String modifyPersonalForm(Model model) throws RecruitmentException {

		CandidatePersonal candidatePersonal = service
				.getCandidatePersonalDetails(candidateId);
		model.addAttribute("candPers", candidatePersonal);
		model.addAttribute("currentDate", Date.valueOf(LocalDate.now()));

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
		Date dateOfBirth = candidatePersonal.getDob();

		candidatePersonal.setDob(DateUtility.parseDate(dateOfBirth));

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
	 * @throws RecruitmentException
	 */
	@RequestMapping("/modifyqualform.htm")
	public String modifyQualificationForm(Model model)
			throws RecruitmentException {

		CandidateQualifications candidateQualification = service
				.getCandidateQualificationDetails(candidateId);
		model.addAttribute("candQual", candidateQualification);
		model.addAttribute("currentYear", LocalDate.now().getYear());
		model.addAttribute("qualifications", Constant.getQualifications());

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
	 * @throws RecruitmentException
	 */
	@RequestMapping("/modifyworkhistform.htm")
	public String modifyWorkHistoryForm(Model model)
			throws RecruitmentException {

		CandidateWorkHistory candidateWorkHistory = service
				.getCandidateWorkHistoryDetails(candidateId);
		model.addAttribute("candWork", candidateWorkHistory);
		model.addAttribute("currentDate", Date.valueOf(LocalDate.now()));
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

		Date employmentFrom = candidateWorkHistory.getEmploymentFrom();
		Date employmentTo = candidateWorkHistory.getEmploymentTo();

		candidateWorkHistory.setEmploymentFrom(DateUtility
				.parseDate(employmentFrom));
		candidateWorkHistory.setEmploymentTo(DateUtility
				.parseDate(employmentTo));
		candidateWorkHistory.setCandidateId(candidateId);
		service.modifycandidWorkHistory(candidateWorkHistory);

		model.addAttribute("candWork", null);
		return "modifyresume";
	}

	/**
	 * When user selects search jobs from his home page he will be directed here
	 * and is sent to searchjobs.jsp
	 * 
	 * @param model
	 * @return searchjobs.jsp
	 */
	@RequestMapping("/search.htm")
	public String searchBy(Model model) {

		model.addAttribute("jobRequirements1", new JobRequirements());
		model.addAttribute("jobRequirements2", new JobRequirements());
		model.addAttribute("jobRequirements3", new JobRequirements());
		model.addAttribute("jobRequirements4", new JobRequirements());
		model.addAttribute("qualifications", Constant.getQualifications());
		model.addAttribute("cities", Constant.getCities());
		return "searchjobs";

	}

	/**
	 * When user clicks on search jobs by qualification in searchjobs.jsp he
	 * will be directed here and is sent to jobs.jsp where a list of jobs
	 * matching his selected criteria will be displayed.
	 * 
	 * @param jobRequirements
	 * @param model
	 * @return jobs.jsp
	 */
	@RequestMapping(value = "/byqualification.htm", method = RequestMethod.POST)
	public String searchByQualification(
			@ModelAttribute("jobRequirements1") JobRequirements jobRequirements,
			Model model) {
		String qualification = jobRequirements.getQualificationRequired();
		List<JobRequirements> jobs = service.getJobByQual(qualification);
		model.addAttribute("jobs", jobs);
		return "jobs";

	}

	/**
	 * When user clicks on search jobs by position in searchjobs.jsp he will be
	 * directed here and is sent to jobs.jsp where a list of jobs matching his
	 * selected criteria will be displayed.
	 * 
	 * @param jobRequirements
	 * @param model
	 * @return jobs.jsp
	 */
	@RequestMapping(value = "/byposition.htm", method = RequestMethod.POST)
	public String searchByPosition(
			@ModelAttribute("jobRequirements2") JobRequirements jobRequirements,
			Model model) {
		String position = jobRequirements.getPositionRequired();
		List<JobRequirements> jobs = service.getJobByPosition(position);
		model.addAttribute("jobs", jobs);
		return "jobs";

	}

	/**
	 * When user clicks on search jobs by experience in searchjobs.jsp he will
	 * be directed here and is sent to jobs.jsp where a list of jobs matching
	 * his selected criteria will be displayed.
	 * 
	 * @param jobRequirements
	 * @param model
	 * @return jobs.jsp
	 */
	@RequestMapping(value = "/byexperience.htm", method = RequestMethod.POST)
	public String searchByExperience(
			@ModelAttribute("jobRequirements3") JobRequirements jobRequirements,
			Model model) {
		int experience = jobRequirements.getExperienceRequired();
		List<JobRequirements> jobs = service.getJobByExperience(experience);
		model.addAttribute("jobs", jobs);
		return "jobs";

	}

	/**
	 * When user clicks on search jobs by location in searchjobs.jsp he will be
	 * directed here and is sent to jobs.jsp where a list of jobs matching his
	 * selected criteria will be displayed.
	 * 
	 * @param jobRequirements
	 * @param model
	 * @return jobs.jsp
	 */
	@RequestMapping(value = "/bylocation.htm", method = RequestMethod.POST)
	public String searchByLocation(
			@ModelAttribute("jobRequirements4") JobRequirements jobRequirements,
			Model model) {
		String location = jobRequirements.getJobLocation();
		List<JobRequirements> jobs = service.getJobByLocation(location);
		model.addAttribute("jobs", jobs);
		return "jobs";

	}

	/**
	 * When in jobs.jsp and the list of jobs is displayed and he selects apply,
	 * program is directed here and the job applied will be saved .
	 * 
	 * @param jobID
	 * @param companyId
	 * @param model
	 * @return candidate.jsp
	 */
	@RequestMapping("/apply.htm")
	public String applyForJob(@RequestParam("jobID") String jobID,
			@RequestParam("companyId") String companyId, Model model) {
		JobApplied jobApplied = new JobApplied();
		jobApplied.setCandidateId("MOH13619");
		jobApplied.setJobId(jobID);
		jobApplied.setCompId(companyId);

		try {
			service.insertApplyJob(jobApplied);
			model.addAttribute("message",
					"Successfully applied for the job with ID:" + jobID);
		} catch (RecruitmentException e) {
			e.printStackTrace();
		}
		return "candidate";
	}
}
