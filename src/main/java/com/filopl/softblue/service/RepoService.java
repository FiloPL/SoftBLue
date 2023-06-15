package com.filopl.softblue.service;

import com.filopl.softblue.model.Repository;
import com.filopl.softblue.model.RepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by T. Filo Zegarlicki on 10.06.2023
 **/

@Service
public class RepoService {

    private final RestTemplate restTemplate;

    @Value("${github.api.token}")
    private String githubApiToken;

    @Autowired
    public RepoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<RepositoryDTO> getRepositoryData(String owner, String repositoryName) {
        String apiUrl = String.format("https://api.github.com/repos/%s/%s", owner, repositoryName);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + githubApiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Repository> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Repository.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Repository gitHubData = response.getBody();

                RepositoryDTO repositoryData = new RepositoryDTO();
                repositoryData.setFullName(gitHubData.getFullName());
                repositoryData.setDescription(gitHubData.getDescription());
                repositoryData.setCloneUrl(gitHubData.getCloneUrl());
                repositoryData.setStars(gitHubData.getStars());

                String createdAt = gitHubData.getCreatedAt();
                if (createdAt != null) {
                    LocalDateTime createdDateTime = LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME);
                    ZoneId zoneId = ZoneId.systemDefault();
                    String formattedCreatedAt = createdDateTime.atZone(zoneId).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                    repositoryData.setCreatedAt(formattedCreatedAt);
                }

                return ResponseEntity.ok(repositoryData);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}