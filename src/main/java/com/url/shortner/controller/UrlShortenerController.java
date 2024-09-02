package com.url.shortner.controller;

import com.url.shortner.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.net.URI;
import com.url.shortner.model.UrlRequest;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlShortenerController {

  @Autowired
  private UrlShortenerService urlShortenerService;

  @PostMapping
  public ResponseEntity<String> createShortUrl(@RequestBody @Valid UrlRequest urlRequest) {
    String shortUrl = urlShortenerService.shortenUrl(urlRequest.getOriginalUrl());
    return ResponseEntity.ok(shortUrl);
  }

  @GetMapping("/{shortUrl}")
  public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
    String originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
    return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
  }
}
