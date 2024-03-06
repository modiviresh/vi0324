package com.os.toolrentalmanagement.component;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageTranslator {
	private static MessageSource messageSource;

	public MessageTranslator(MessageSource messageSource){
		MessageTranslator.messageSource = messageSource;
	}

	public static String getMessage(String msgCode) {
		return messageSource.getMessage(msgCode, null, null);
	}
}
