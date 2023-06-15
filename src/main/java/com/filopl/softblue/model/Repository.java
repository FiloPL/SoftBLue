package com.filopl.softblue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by T. Filo Zegarlicki on 10.06.2023
 **/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("stargazers_count")
    private int stars;

    @JsonProperty("created_at")
    private String createdAt;

}
