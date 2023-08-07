package com.blackCloffer.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.blackCloffer.binding.BindingObject;
import com.blackCloffer.entities.EntityData;
import com.blackCloffer.service.ServiceInterface;
import com.blackCloffer.util.DataUtils;

@Controller
public class MyController {
	@Autowired
	private JobLauncher launcher;
	@Autowired
	private Job job;
	@Autowired
	private ServiceInterface service;
	@Autowired
	private ServletContext context;
	@Autowired
	private DataUtils util;

	@GetMapping("/")
	public void loadData() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters parameter = new JobParametersBuilder().addLong("start at", System.currentTimeMillis())
				.toJobParameters();
		launcher.run(job, parameter);
	}

	@GetMapping("/index")
	public String loadData(@ModelAttribute("data") BindingObject data, Model model) {
		init(model);
		return "index.html";
	}

	private void init(Model model) {
		model.addAttribute("data", new BindingObject());
		model.addAttribute("yearList", service.endYearList());
		model.addAttribute("topicList", service.topicList());
		model.addAttribute("sectorList", service.sectorList());
		model.addAttribute("sourceList", service.sourceList());
		model.addAttribute("pestleList", service.pestleList());
		model.addAttribute("regionList", service.regionList());
		model.addAttribute("swotList", service.swotList());
	}

	@GetMapping("/search")
	public String searchData(Model model) {
		model.addAttribute("dataList", service.getList());
		return "Result.html";
	}

	@PostMapping("/getChart")
	public String drawCharts(@ModelAttribute("data") BindingObject data, Model model) {
		model.addAttribute("data", new BindingObject());
		List<EntityData> dataList = service.searchResult(data);
		List<Integer> intensity = new ArrayList<>();
		List<Integer> impact = new ArrayList<>();
		List<Integer> relevance = new ArrayList<>();
		List<Integer> likelihood = new ArrayList<>();
		Set<Integer> yearSet = new LinkedHashSet<>();
		Set<String> regionSet = new LinkedHashSet<>();
		for (EntityData ob : dataList) {
			intensity.add(Integer.valueOf(ob.getIntensity()));
			impact.add(Integer.valueOf(ob.getImpact()));
			relevance.add(Integer.valueOf(ob.getRelevance()));
			likelihood.add(Integer.valueOf(ob.getLikelihood()));
			yearSet.add(Integer.valueOf(ob.getEndYear()));
			regionSet.add(ob.getRegion());
		}
		Integer intensityCount = intensity.stream().mapToInt(Integer::intValue).sum();
		Integer impactCount = impact.stream().mapToInt(Integer::intValue).sum();
		Integer relevanceCount = relevance.stream().mapToInt(Integer::intValue).sum();
		Integer likelihoodCount = likelihood.stream().mapToInt(Integer::intValue).sum();
		// model.addAttribute("intensityList", intensityList);
		model.addAttribute("dataSet", dataList);
		model.addAttribute("intensityCount", intensityCount);
		model.addAttribute("impactCount", impactCount);
		model.addAttribute("relevanceCount", relevanceCount);
		model.addAttribute("likelihoodCount", likelihoodCount);
		model.addAttribute("yearSet", yearSet);
		model.addAttribute("regionSet", regionSet);
		return "Charts.html";
	}

}
