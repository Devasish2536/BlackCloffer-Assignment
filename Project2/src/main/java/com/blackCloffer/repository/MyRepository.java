package com.blackCloffer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blackCloffer.entities.EntityData;

public interface MyRepository extends JpaRepository<EntityData, Integer> {
	
	@Query("select distinct endYear from EntityData order by endYear")
	public List<Integer>getYearList();
	@Query("select distinct topic from EntityData ")
	public List<String>topicList();
	@Query("select distinct sector from EntityData ")
	public List<String>sectorList();
	@Query("select distinct region from EntityData ")
	public List<String>regionList();
	@Query("select distinct pestle from EntityData ")
	public List<String>pestleList();
	@Query("select distinct swot from EntityData ")
	public List<String>swotList();
	@Query("select distinct source from EntityData ")
	public List<String>sourceList();
	 

}
