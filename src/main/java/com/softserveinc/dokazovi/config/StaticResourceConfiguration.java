package com.softserveinc.dokazovi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Path;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

	@Value("${fs.root}")
	private String fsPath;

	@Value("${fs.assets.path}")
	private String fsAssetsPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/" + fsAssetsPath + "/**")
				.addResourceLocations("file:///" + Path.of(fsPath).toAbsolutePath() + File.separator);
	}
}
