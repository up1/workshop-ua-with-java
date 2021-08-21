package com.example.uaspringboot;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class DemoHttpRequest {

    public void call(String domain) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String result = restTemplate.getForObject("http://" + domain, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

}
