package org.piotr.github.model.pojo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class RepoDetailsDeserializer extends StdDeserializer<RepoDetails> {

    public RepoDetailsDeserializer() {
        this(null);
    }

    public RepoDetailsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RepoDetails deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
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
