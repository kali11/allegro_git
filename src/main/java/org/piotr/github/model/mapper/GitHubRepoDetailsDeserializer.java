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

/**
 * Custom deserializer for JSON acquired from GitHubAPI
 */
public class GitHubRepoDetailsDeserializer extends JsonDeserializer<RepoDetails> {

    @Override
    public RepoDetails deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        RepoDetails repoDetails = new RepoDetails();
        JsonNode node = jp.getCodec().readTree(jp);
        try {
            repoDetails.setFullName(node.get("full_name").asText());
            repoDetails.setDescription(node.get("description").asText());
            repoDetails.setCloneUrl(node.get("clone_url").asText());
            repoDetails.setStars(node.get("stargazers_count").asLong());
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(node.get("created_at").asText());
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
            repoDetails.setCreatedAt(formatter.format(zonedDateTime));
        } catch (NullPointerException e) {
            throw new DeserializationException(node.toString(), e);
        }
        return repoDetails;
    }
}
