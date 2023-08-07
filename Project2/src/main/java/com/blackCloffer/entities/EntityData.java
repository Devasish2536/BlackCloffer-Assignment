package com.blackCloffer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EntityData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String endYear;
	private String cityLng;
	private String citylat;
	private String intensity;
	private String  sector;
	private String  topic;
	private String  insight;
	private String swot;
	private String url;
	private String region;
	private String startyear;
	private Integer impact;
	private String added;
	private String published;
	private String 	city;
	private String country;
	private Integer relevance;
	private String pestle;
	private String source;
	private String	title;
	private Integer likelihood;
}

