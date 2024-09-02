package com.url.shortner.service;

import com.url.shortner.model.UrlEntity;
import com.url.shortner.repository.UrlRepository;
import com.url.shortner.util.Base62Encoder;
import com.url.shortner.util.UrlValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.url.shortner.exception.UrlNotFoundException;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UrlValidatorUtil urlValidatorUtil;

    @Autowired
    private Base62Encoder base62Encoder;

    @Override
    public String shortenUrl(String originalUrl) {
        urlValidatorUtil.validateUrl(originalUrl);
        String shortUrl = base62Encoder.encode(originalUrl.hashCode());
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setOriginalUrl(originalUrl);
        urlEntity.setShortUrl(shortUrl);
        urlRepository.save(urlEntity);
        return shortUrl;
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found")).getOriginalUrl();
    }
}
