package com.galvin.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SurveyController {

	@RequestMapping("/")
	public String dojoSurvey() {
		
		return "/survey.jsp";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String result(@RequestParam(value="name") String userName, @RequestParam(value="location") String userLocation, @RequestParam(value="language") String userLanguage, @RequestParam(value="comment") String userComment, Model model ) {
		model.addAttribute("userName", userName);
		model.addAttribute("userLocation", userLocation);
		model.addAttribute("userLanguage", userLanguage);
		model.addAttribute("userComment", userComment);
		return "/result.jsp";
	}
}
