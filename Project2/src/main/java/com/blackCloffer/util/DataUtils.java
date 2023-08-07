package com.blackCloffer.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class DataUtils {
	public void generatePie(String imgPath,List<Object[]>data) throws IOException {
		// create dataset for pie
		DefaultPieDataset dataset=new DefaultPieDataset();
		for(Object[] ob:data) {
			dataset.setValue(String.valueOf(ob[0]), Double.valueOf(ob[1].toString()));
		}
		// create jfreechat object
		JFreeChart chart=ChartFactory.createPieChart3D("End_year and Intensity", dataset,false,false,false);
		// convert jfreechat object to img
		ChartUtilities.saveChartAsJPEG(new File(imgPath+"/pie.jpg"), chart, 500, 300);
	}

}
