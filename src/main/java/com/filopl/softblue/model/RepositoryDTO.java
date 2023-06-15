package com.filopl.softblue.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by T. Filo Zegarlicki on 10.06.2023
 **/

@Getter
@Setter
public class RepositoryDTO {

    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;
}
