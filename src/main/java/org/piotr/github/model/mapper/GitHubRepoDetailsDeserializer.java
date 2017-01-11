package org.piotr.github.model.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.piotr.github.model.pojo.RepoDetails;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class GitHubRepoDetailsDeserializer extends JsonDeserializer<RepoDetails> {

    @Override
    public RepoDetails deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        RepoDetails repoDetails = new RepoDetails();
        JsonNode node = jp.getCodec().readTree(jp);
        repoDetails.setName(node.get("full_name").asText());
        repoDetails.setDescription(node.get("description").asText());
        repoDetails.setCloneUrl(node.get("clone_url").asText());
        repoDetails.setStargazers(node.get("stargazers_count").asLong());
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(node.get("created_at").asText());
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        repoDetails.setCreationDate(formatter.format(zonedDateTime));
        return repoDetails;
    }
}
