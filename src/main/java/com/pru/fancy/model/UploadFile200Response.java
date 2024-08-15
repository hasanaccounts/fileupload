package com.pru.fancy.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UploadFile200Response
 */

@JsonTypeName("uploadFile_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-15T21:17:23.650425255Z[GMT]")
public class UploadFile200Response {

  private String filePath;

  public UploadFile200Response filePath(String filePath) {
    this.filePath = filePath;
    return this;
  }

  /**
   * The path where the uploaded file is stored.
   * @return filePath
  */
  
  @Schema(name = "FilePath", example = "C:\\Path\\To\\UploadedFiles\\example.txt", description = "The path where the uploaded file is stored.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("FilePath")
  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadFile200Response uploadFile200Response = (UploadFile200Response) o;
    return Objects.equals(this.filePath, uploadFile200Response.filePath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filePath);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UploadFile200Response {\n");
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
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
