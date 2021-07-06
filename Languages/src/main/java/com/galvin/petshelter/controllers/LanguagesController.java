package com.galvin.petshelter.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galvin.petshelter.models.Language;
import com.galvin.petshelter.services.LanguageService;

@Controller
public class LanguagesController {

private final LanguageService languageService;
	
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}
	
	@RequestMapping("/languages")
	public String index(Model model, @ModelAttribute("language") Language language) {
		List<Language> allLanguages = this.languageService.allLanguages();
		System.out.println("**********");
		System.out.println(allLanguages);
		model.addAttribute("allLanguages", allLanguages);
		return "home.jsp";
	}
	
	@PostMapping("/languages/create")
	public String create(Model model, @Valid @ModelAttribute("language") Language language, BindingResult result) {
		if (result.hasErrors()) {
			List<Language> allLanguages = this.languageService.allLanguages();
			model.addAttribute("allLanguages", allLanguages);
			return "home.jsp";
		} else {
			this.languageService.createLanguage(language);
			return "redirect:/languages";
		}
	}
	
	@GetMapping("/languages/{id}")
	public String getOneLanguage(@PathVariable("id") Long id, Model model) {
		Language l = this.languageService.getOneLanguage(id);
		model.addAttribute("languageToShow", l);
		return "languageinfo.jsp";
	}
	
	@GetMapping("/languages/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Language l = this.languageService.getOneLanguage(id);
		model.addAttribute("language", l);
		return "edit.jsp";
	}
	
	@PostMapping("/languages/{id}/update")
	public String update(@Valid @ModelAttribute("language") Language language, BindingResult result) {
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {
			this.languageService.updateLanguage(language);
			return "redirect:/languages";
		}
	}
	
	@GetMapping("/languages/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		this.languageService.deleteLanguage(id);
		return "redirect:/languages";
	}
}
