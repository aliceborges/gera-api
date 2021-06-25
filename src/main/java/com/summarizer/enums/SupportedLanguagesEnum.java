package com.summarizer.enums;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum SupportedLanguagesEnum {
    BRAZILIAN_PORTUGUESE("pt", "BR"),
    ENGLISH("en", "US");

    private String language;
    private String country;

    SupportedLanguagesEnum (String language, String country) {
        this.language = language;
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }
}
