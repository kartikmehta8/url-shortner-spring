package com.url.shortner.service;

public interface UrlShortenerService {
  String shortenUrl(String originalUrl);

  String getOriginalUrl(String shortUrl);
}
