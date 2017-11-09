package com.cg.recruitment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.recruitment.entities.Login;
import com.cg.recruitment.exception.RecruitmentException;
import com.cg.recruitment.service.IServiceDao;
import com.cg.recruitment.util.Constant;

@Controller
public class RecruitmentController {

	final String LOGIN = "login";

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

			return "success";
		}
	}
}
