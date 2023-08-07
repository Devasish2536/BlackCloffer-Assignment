package com.blackCloffer.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindingObject {
	private String endYear;
	private String topic;
	private String sector;
	private String source;
	private String pestle;
	private String swot;
	private String region;

}
