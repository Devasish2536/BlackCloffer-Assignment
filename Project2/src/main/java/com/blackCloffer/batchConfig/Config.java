package com.blackCloffer.batchConfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.blackCloffer.entities.EntityData;
import com.blackCloffer.processor.DataProcessor;
import com.blackCloffer.repository.MyRepository;

@Configuration
@EnableBatchProcessing
public class Config {
	@Autowired
	private MyRepository repo;
	@Bean
	public FlatFileItemReader<EntityData> itemreader(){
		FlatFileItemReader<EntityData> fileReader=new FlatFileItemReader<>();
		fileReader.setResource(new FileSystemResource("src/main/resources/Data.csv"));
		fileReader.setName("Cloffer_Data");
		fileReader.setLinesToSkip(1);
		fileReader.setLineMapper(lineMapper());
		return fileReader;
		
	}private LineMapper<EntityData> lineMapper() {
		DefaultLineMapper<EntityData>mapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer tokenizer=new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setStrict(false);
		tokenizer.setNames("end_year","citylng","citylat","intensity","sector","topic","insight","swot","url","region","start_year","impact","added","published","	city","country","relevance",
				"pestle","source","	title","likelihood");
		BeanWrapperFieldSetMapper<EntityData> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(EntityData.class);
		mapper.setFieldSetMapper(fieldSetMapper);
		mapper.setLineTokenizer(tokenizer);
		return mapper;
	}
	@Bean
	public DataProcessor processor() {
		return new DataProcessor();
	}
	@Bean
	public RepositoryItemWriter<EntityData> itemwriter(){
		RepositoryItemWriter<EntityData>fileWriter=new RepositoryItemWriter<>();
		fileWriter.setRepository(repo);
		fileWriter.setMethodName("save");
		return fileWriter;
	}
	@Autowired
	private StepBuilderFactory stepFactory;
	@Bean
	public Step step() {
		return stepFactory.get("Step-1").<EntityData,EntityData>chunk(25)
				.reader(itemreader())
				.processor(processor())
				.writer(itemwriter()).build();
	}
	@Autowired
	private JobBuilderFactory jobFactory;
	@Bean
	public Job job() {
		return jobFactory.get("clofferData_import")
				.flow(step())
				.end().build();
	}

}
