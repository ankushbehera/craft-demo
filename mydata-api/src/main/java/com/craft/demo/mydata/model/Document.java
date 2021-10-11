package com.craft.demo.mydata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;
import org.springframework.data.mongodb.core.mapping.TimeSeries;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.System.*;
import static java.lang.System.currentTimeMillis;

/**
 * Document
 */
@org.springframework.data.mongodb.core.mapping.Document("document")
@Sharded(shardKey = { "timestamp" },shardingStrategy = ShardingStrategy.HASH)
@CompoundIndexes({
        @CompoundIndex(name = "IdSearchIndex", def = "{'timestamp' : 1, 'attributes.key': 1,'attributes.value': 1}")
})
@Builder
public class Document   {
  @Id
  @JsonProperty("id")
  private String id;

  @JsonProperty("attributes")
  @Valid
  private List<Attribute> attributes = new ArrayList<>();

  @JsonProperty("timestamp")
  private Long timestamp=Instant.now().toEpochMilli();

  public Document id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @ApiModelProperty(example = "2aa33f3r5", required = true, value = "")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Document attributes(List<Attribute> attributes) {
    this.attributes = attributes;
    return this;
  }

  public Document addAttributesItem(Attribute attributesItem) {
    this.attributes.add(attributesItem);
    return this;
  }

  /**
   * Get attributes
   * @return attributes
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Attribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<Attribute> attributes) {
    this.attributes = attributes;
  }

  public Document timestamp(Long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
   */
  @ApiModelProperty(example = "1633535440", value = "")
  @NotNull
  @Valid
  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return Objects.equals(this.id, document.id) &&
            Objects.equals(this.attributes, document.attributes) &&
            Objects.equals(this.timestamp, document.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, attributes, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(getTimestamp())).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

