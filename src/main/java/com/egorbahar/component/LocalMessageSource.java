package com.egorbahar.component;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocalMessageSource {
    private MessageSource messageSource;

    public LocalMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String messageCode, Object[] arguments) {
        Locale locale = new Locale("En");
        return messageSource.getMessage(messageCode, arguments, locale);
    }
}