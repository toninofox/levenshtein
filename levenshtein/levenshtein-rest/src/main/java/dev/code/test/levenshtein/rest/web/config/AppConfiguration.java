package dev.code.test.levenshtein.rest.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import dev.code.test.levenshtein.common.IEnvironment;
import dev.code.test.levenshtein.common.IEnvironmentBehaviour;
import dev.code.test.levenshtein.rest.web.config.service.SpringConfigPlaceholder;

@Configuration
@ComponentScan(basePackageClasses = {SpringConfigPlaceholder.class})
public class AppConfiguration {

	@Bean
	public IEnvironment getEnvironment(){
		return new IEnvironment() {
			@Override
			public void run(IEnvironmentBehaviour envBehavior) {
				/*	implement some kind of logic (environment variable,by hostname, ecc) to select the correct environment. 
				 * 	For demo purpose, I will use always DEV as environment
				 */
				envBehavior.doForDev();
			}
		};
	}
	
}
