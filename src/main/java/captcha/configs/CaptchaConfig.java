package captcha.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import captcha.domain.Captcha;
import captcha.domain.CaptchaFactory;
import captcha.validators.CaptchaValidator;

@Configuration
public class CaptchaConfig {
	
	@Bean
	@Scope("singleton")
	public CaptchaFactory factory() {
		return new CaptchaFactory();
	}

	@Bean
	@Scope("prototype")
	public Captcha captcha() {
		return factory().random();
	}
<<<<<<< HEAD
	
	@Bean
	public CaptchaValidator validator() {
=======
        
        @Bean
	@Scope("prototype")
	public CaptchaValidator captchaValidator() {
>>>>>>> demo
		return new CaptchaValidator(factory());
	}
	
}
