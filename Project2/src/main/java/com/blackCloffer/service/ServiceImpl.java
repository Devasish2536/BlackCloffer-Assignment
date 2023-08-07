package com.blackCloffer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.blackCloffer.binding.BindingObject;
import com.blackCloffer.entities.EntityData;
import com.blackCloffer.repository.MyRepository;
@Service
public class ServiceImpl implements ServiceInterface {
	@Autowired
	private MyRepository repo;

	public List<Integer> endYearList() {
		return repo.getYearList();
	}

	public List<String> topicList() {
		return repo.topicList();
	}

	public List<String> sectorList() {
		return repo.sectorList();
	}

	public List<String> regionList() {
		return repo.regionList();
	}

	public List<String> sourceList() {
		return repo.sourceList();
	}

	public List<String> pestleList() {
		return repo.pestleList();
	}

	public List<String> swotList() {
		return repo.swotList();
	}

	public List<EntityData> searchResult(BindingObject data) {
		EntityData entitydata=new EntityData();
		if(null!=data.getEndYear()&& !"".equals(data.getEndYear()))
			entitydata.setEndYear(data.getEndYear());
		if(null!=data.getTopic()&& !"".equals(data.getTopic()))
			entitydata.setTopic(data.getTopic());
		if(null!=data.getSector()&& !"".equals(data.getSector()))
			entitydata.setSector(data.getSector());
		if(null!=data.getRegion()&& !"".equals(data.getRegion()))
			entitydata.setRegion(data.getRegion());
		if(null!=data.getSource()&& !"".equals(data.getSource()))
			entitydata.setSource(data.getSource());
		if(null!=data.getPestle()&& !"".equals(data.getPestle()))
			entitydata.setPestle(data.getPestle());
		if(null!=data.getSwot()&& !"".equals(data.getSwot()))
			entitydata.setSwot(data.getSwot());
		return repo.findAll(Example.of(entitydata));
	}
	
	public List<EntityData> getList() {
		return repo.findAll();
	}

}
