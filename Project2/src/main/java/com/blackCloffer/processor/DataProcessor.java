package com.blackCloffer.processor;

import org.springframework.batch.item.ItemProcessor;

import com.blackCloffer.entities.EntityData;

public class DataProcessor implements ItemProcessor<EntityData, EntityData> {

	@Override
	public EntityData process(EntityData item) throws Exception {
			
		return item;
	}

}
