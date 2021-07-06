package com.galvin.petshelter.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.galvin.petshelter.models.Language;
import com.galvin.petshelter.repositories.LanguagesRepository;

@Service
public class LanguageService {

	private final LanguagesRepository languagesRepo;
	
	public LanguageService(LanguagesRepository languagesRepo) {
		this.languagesRepo = languagesRepo;
	}
	
	public List<Language> allLanguages(){
		return this.languagesRepo.findAll();
	}
	
	public Language createLanguage(Language l) {
		return this.languagesRepo.save(l);
	}
	
	public Language updateLanguage(Language l) {
		return this.languagesRepo.save(l);
	}
	
	public Language getOneLanguage(Long id) {
		return this.languagesRepo.findById(id).orElse(null);
	}
	
	public void deleteLanguage(Long id) {
		this.languagesRepo.deleteById(id);
	}
}
