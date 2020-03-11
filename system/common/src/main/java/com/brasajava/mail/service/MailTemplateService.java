package com.brasajava.mail.service;

import java.util.Map;



public interface MailTemplateService {
	

	String build(Map<String, Object> params, String template);

}
