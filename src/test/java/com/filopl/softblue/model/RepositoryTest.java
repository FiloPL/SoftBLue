package com.filopl.softblue.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by T. Filo Zegarlicki on 10.06.2023
 **/

class RepositoryTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        String fullName = "Bella/chrupki";
        String description = "Language model developed by Cat";
        String cloneUrl = "https://github.com/Bella/chrupki.git";
        int stars = 100;
        String createdAt = "2022-01-01";

        // Act
        Repository repository = new Repository();
        repository.setFullName(fullName);
        repository.setDescription(description);
        repository.setCloneUrl(cloneUrl);
        repository.setStars(stars);
        repository.setCreatedAt(createdAt);

        // Assert
        Assertions.assertEquals(fullName, repository.getFullName());
        Assertions.assertEquals(description, repository.getDescription());
        Assertions.assertEquals(cloneUrl, repository.getCloneUrl());
        Assertions.assertEquals(stars, repository.getStars());
        Assertions.assertEquals(createdAt, repository.getCreatedAt());
    }

    @Test
    public void testToString() {
        // Arrange
        String fullName = "Forest/lina";
        String description = "Language model developed by Forest";
        String cloneUrl = "https://github.com/Forest/lina.git";
        int stars = 100;
        String createdAt = "2022-01-01";

        Repository repository = new Repository();
        repository.setFullName(fullName);
        repository.setDescription(description);
        repository.setCloneUrl(cloneUrl);
        repository.setStars(stars);
        repository.setCreatedAt(createdAt);

        // Act
        String toString = repository.toString();

        // Assert
        Assertions.assertTrue(toString.contains(fullName));
        Assertions.assertTrue(toString.contains(description));
        Assertions.assertTrue(toString.contains(cloneUrl));
        Assertions.assertTrue(toString.contains(Integer.toString(stars)));
        Assertions.assertTrue(toString.contains(createdAt));
    }
}
