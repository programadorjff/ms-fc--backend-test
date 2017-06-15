package com.scmspain.configuration;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.export.MBeanExporter;

import com.scmspain.MsFcTechTestApplication;

@Configuration
@Import({ MsFcTechTestApplication.class })
public class TestConfiguration {

	@Bean
	public MBeanExporter mockExporter() {
		return mock(MBeanExporter.class);
	}

}
