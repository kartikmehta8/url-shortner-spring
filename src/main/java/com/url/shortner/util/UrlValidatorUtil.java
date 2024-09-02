package com.url.shortner.util;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

@Component
public class UrlValidatorUtil {

  private static final UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);

  public void validateUrl(String url) {
    if (!urlValidator.isValid(url)) {
      throw new IllegalArgumentException("Invalid URL format");
    }
  }
}
