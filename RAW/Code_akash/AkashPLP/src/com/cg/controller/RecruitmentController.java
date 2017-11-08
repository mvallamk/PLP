package com.cg.controller;

import java.util.ArrayList;

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

import com.cg.entities.Login;
import com.cg.exception.RecruitmentException;
import com.cg.jpautil.Constant;
import com.cg.service.IServiceDao;

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
	 * 
	 * 
	 * @param model
	 * @param loginId
	 * @param password
	 * @return success
	 * @exception 
	 */
	@RequestMapping(value = "/checklogin.htm", method = RequestMethod.POST)
	public String login(Model model, @RequestParam("loginId") String loginId,@RequestParam("password") String password) 
	{
		Login login=new Login();
		login.setLoginId(loginId);
		login.setPassword(password);
		
		if (!service.validateLoginDetails(login.getLoginId(),
				login.getPassword())) {
			model.addAttribute("message", "Invalid Login Details");
			return LOGIN;
		} else {

			
			login.setType(service.getLoginDetails(login.getLoginId()).getType());
			model.addAttribute("loginId", login.getLoginId());

			if("User".equals(login.getType()))
			return "candidate";
			else
				if("Company".equals(login.getType()))
					return "company";
				else 
					return "admin";
		}
	}

	@RequestMapping("/showsignupform.htm")
	public String showsignupPage(Model model) {

	/*	Constant.cityList.add("Pune");
		Constant.cityList.add("Mumbai");
		Constant.cityList.add("Bangalore");
		Constant.cityList.add("Hyderabad");
		Constant.cityList.add("Chennai");
		Constant.cityList.add("Delhi");
		Constant.cityList.add("Gurugram");
		*/
		
		
		
		model.addAttribute("typeList", Constant.getTypeList());
		model.addAttribute(LOGIN, new Login());
		return "signup";
	}

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
