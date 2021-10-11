package com.craft.demo.mydata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Attribute
 */
@Builder
@AllArgsConstructor
public class Attribute   {
  @JsonProperty("key")
  private String key;

  @JsonProperty("value")
  private String value;

  public Attribute key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Get key
   * @return key
   */
  @ApiModelProperty(example = "a21kjl23a", required = true, value = "")
  @NotNull

  @Size(min=1,max=256)
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Attribute value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   */
  @ApiModelProperty(example = "a4llak42q", required = true, value = "")
  @NotNull

  @Size(min=1,max=256)
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Attribute attribute = (Attribute) o;
    return Objects.equals(this.key, attribute.key) &&
            Objects.equals(this.value, attribute.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Attribute {\n");

    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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