package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

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
	int status=1;
	List<CandidateQualifications> qualList=new ArrayList<CandidateQualifications>();


	@Autowired
	IServiceDao service;
	
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
	
	
	@RequestMapping("/addresumeform.htm")
	public String candidateAddResumeForm(Model model)
	{
		/*
		CandidateQualifications candQual1=null;
		CandidateQualifications candQual2=null;
		CandidateQualifications candQual3=null;
		CandidateQualifications candQual4=null;
		if(status==1)
		{
			candQual1=new CandidateQualifications();
		}
		if(status==2)
		{
			candQual2=new CandidateQualifications();
		}
		if(status==3)
		{
			candQual3=new CandidateQualifications();
		}
		//candQual.setCollegeName("Your Name");
		model.addAttribute("candPers",new CandidatePersonal());
		model.addAttribute("candQual1",candQual1);
		model.addAttribute("candQual2",candQual2);
		model.addAttribute("candQual3",candQual3);
		model.addAttribute("candQual4",candQual4);
		model.addAttribute("candWork", new CandidateWorkHistory());
		
	
		//qualList.add(c1);
		
		//model.addAttribute("qualList", qualList);
		//qualList.add(candQual);
		return "addresume";*/
		
	}
	
	@RequestMapping("/addqual.htm")
	public String candidateAddResumeForm(Model model)
	{
		status++;
		return "addresume";
	}


}
