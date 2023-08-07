package com.blackCloffer.service;

import java.util.List;

import com.blackCloffer.binding.BindingObject;
import com.blackCloffer.entities.EntityData;

public interface ServiceInterface {
public List<Integer>endYearList();
public List<String>topicList();
public List<String>sectorList();
public List<String>regionList();
public List<String>sourceList();
public List<String>pestleList();
public List<String>swotList();
public List<EntityData>searchResult(BindingObject data);
public List<EntityData>getList();
	
}
