package captcha.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import captcha.domain.Captcha;
import captcha.models.CaptchaForm;
import captcha.validators.CaptchaValidator;

@Controller
@Scope("prototype")
public class CaptchaController {

	private static final String FORM_OBJECT = "captchaForm";

	private static final String FORM_PAGE = "captcha-form";
	private static final String SUCCESS_PAGE = "captcha-correct";


	@Autowired
	Captcha captcha;
	
	@Autowired
	CaptchaValidator validator;
	
	
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public String show(Model model) {
		setupCaptchaForm(new CaptchaForm(), model);
		return FORM_PAGE;
	}

	@RequestMapping(value = "/captcha", method = RequestMethod.POST)
	public String answer(@Valid @ModelAttribute(FORM_OBJECT) CaptchaForm captchaForm, Errors errors, Model model) {
		if (errors.hasErrors()) {
			setupCaptchaForm(captchaForm, model);
			return FORM_PAGE;
		}
		
		return SUCCESS_PAGE;
	}
	

	private void setupCaptchaForm(CaptchaForm captchaForm, Model model) {
		captchaForm.setId(captcha.getId());
		captchaForm.setQuestion(captcha.getText());
		model.addAttribute(FORM_OBJECT, captchaForm);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
}
