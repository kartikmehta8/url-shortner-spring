package com.url.shortner.util;

import org.springframework.stereotype.Component;

@Component
public class Base62Encoder {

  private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public String encode(int hashCode) {
    StringBuilder shortUrl = new StringBuilder();
    while (hashCode > 0) {
      shortUrl.append(BASE62.charAt(hashCode % 62));
      hashCode /= 62;
    }
    return shortUrl.reverse().toString();
  }
}
