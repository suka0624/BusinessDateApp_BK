package com.suka.springboot;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.suka.springboot.repositories.BusinessDateRepository;

@Controller
public class BusinessDateController {
	
	@Autowired
	BusinessDateRepository repository;

	// 初期画面(一覧画面)表示
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") BusinessDate businessDate, 
			ModelAndView mav) {
		mav.setViewName("index");
		Iterable<BusinessDate> list = repository.findAll();
		mav.addObject("datalist",list);
		return mav;
	}
	
	// 登録処理
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView save(
			@ModelAttribute("formModel") BusinessDate businessDate) {
		repository.saveAndFlush(businessDate);
		return new ModelAndView("redirect:/");
	}
	
	// 編集画面表示
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute BusinessDate businessDate,
			@PathVariable int id, ModelAndView mav) {
		mav.setViewName("edit");
		Optional<BusinessDate> data = repository.findById((long)id);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	// 更新処理
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute BusinessDate businessDate) {
		repository.saveAndFlush(businessDate);
		return new ModelAndView("redirect:/");
	}
	
	// 削除確認画面
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id,
			ModelAndView mav) {
		mav.setViewName("delete");
		Optional<BusinessDate> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	
	// 削除処理
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView remove(@RequestParam long id) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
	
	// 初期化処理
	@PostConstruct
	public void init() {
		// 初期データ1
		BusinessDate d1 = new BusinessDate();
		d1.setName("加算");
		d1.setFormula("x + x;");
		repository.saveAndFlush(d1);
		
		// 初期データ2
		BusinessDate d2 = new BusinessDate();
		d2.setName("乗算");
		d2.setFormula("x * x;");
		repository.saveAndFlush(d2);
		
		//初期データ3
		BusinessDate d3 = new BusinessDate();
		d3.setName("10倍");
		d3.setFormula("x * 10;");
		repository.saveAndFlush(d3);
	}
}
