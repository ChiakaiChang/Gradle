package com.symphox.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.symphox.service.HelloWorldService;


@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;

	@Autowired
	public WelcomeController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model, HttpServletRequest request) {
		logger.debug("index() is executed!!!!!!!!!");
		
		if (request.getSession().getAttribute("time") == null) {
	            request.getSession().setAttribute("time", new Byte[1024 * 1024 * 30]);
	            logger.error("Save session !");
	        } 
		
		model.put("title", helloWorldService.getTitle(""));
		model.put("msg", helloWorldService.getDesc() + " Login Time : " + request.getSession().getAttribute("time"));
		
		return "index";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView hello(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		return model;

	}

}
