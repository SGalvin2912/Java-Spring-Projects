package com.galvin.dojosandninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.galvin.dojosandninjas.models.Dojo;
import com.galvin.dojosandninjas.models.Ninja;
import com.galvin.dojosandninjas.services.DojoService;
import com.galvin.dojosandninjas.services.NinjaService;

@Controller
public class DojosAndNinjasController {

	private final DojoService dojoService;
	
	private final NinjaService ninjaService;
	
	public DojosAndNinjasController(DojoService dojoService, NinjaService ninjaService) {
		this.dojoService = dojoService;
		this.ninjaService = ninjaService;
	}
	
	@GetMapping("/")
	public String welcomeToDojo(Model model, @ModelAttribute("dojo") Dojo dojo) {
		List<Dojo> allDojos = this.dojoService.allDojos();
		model.addAttribute("allDojos", allDojos);
		return "index.jsp";
	}
	
	@PostMapping("/dojos/create")
	public String createNewDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<Dojo> allDojos = this.dojoService.allDojos();
			model.addAttribute("allDojos", allDojos);
			return "index.jsp";
		}else {
			this.dojoService.createDojo(dojo);
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> allDojos = this.dojoService.allDojos();
		model.addAttribute("allDojos", allDojos);
		return "newNinja.jsp";
	}
	
	@PostMapping("/ninjas/create")
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
		if(result.hasErrors()) {
			return "newNinja.jsp";
		}else {
			this.ninjaService.createNinja(ninja);
			return "redirect:/";
		}
	}
	
	@GetMapping("/dojos/{id}/info")
	public String dojoInfo(@PathVariable("id") Long id, Model model) {
		Dojo d = this.dojoService.getOneDojo(id);
		
		model.addAttribute("dojoToShow", d);
		return "dojoDetails.jsp";
	}
}
