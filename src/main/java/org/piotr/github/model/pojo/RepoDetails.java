package org.piotr.github.model.pojo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

@JsonDeserialize(using = RepoDetailsDeserializer.class)
public class RepoDetails {
    private String name;
    private String description;
    private String cloneUrl;
    private Long stargazers;
    private String creationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public Long getStargazers() {
        return stargazers;
    }

    public void setStargazers(Long stargazers) {
        this.stargazers = stargazers;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
