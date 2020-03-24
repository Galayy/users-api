package com.itechart.container.spring.users.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Must be valid JWT tokens
 */
@ApiModel(description = "Must be valid JWT tokens")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-03-24T13:41:48.118567+03:00[Europe/Minsk]")

public class TokenResponse   {
  @JsonProperty("accessToken")
  private String accessToken;

  @JsonProperty("refreshToken")
  private String refreshToken;

  @JsonProperty("accessTokenExpirationDate")
  private java.time.Instant accessTokenExpirationDate;

  @JsonProperty("refreshTokenExpirationDate")
  private java.time.Instant refreshTokenExpirationDate;

  public TokenResponse accessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  /**
   * Living 1 hour
   * @return accessToken
  */
  @ApiModelProperty(value = "Living 1 hour")


  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public TokenResponse refreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  /**
   * Living 100 hours
   * @return refreshToken
  */
  @ApiModelProperty(value = "Living 100 hours")


  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public TokenResponse accessTokenExpirationDate(java.time.Instant accessTokenExpirationDate) {
    this.accessTokenExpirationDate = accessTokenExpirationDate;
    return this;
  }

  /**
   * Get accessTokenExpirationDate
   * @return accessTokenExpirationDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public java.time.Instant getAccessTokenExpirationDate() {
    return accessTokenExpirationDate;
  }

  public void setAccessTokenExpirationDate(java.time.Instant accessTokenExpirationDate) {
    this.accessTokenExpirationDate = accessTokenExpirationDate;
  }

  public TokenResponse refreshTokenExpirationDate(java.time.Instant refreshTokenExpirationDate) {
    this.refreshTokenExpirationDate = refreshTokenExpirationDate;
    return this;
  }

  /**
   * Get refreshTokenExpirationDate
   * @return refreshTokenExpirationDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public java.time.Instant getRefreshTokenExpirationDate() {
    return refreshTokenExpirationDate;
  }

  public void setRefreshTokenExpirationDate(java.time.Instant refreshTokenExpirationDate) {
    this.refreshTokenExpirationDate = refreshTokenExpirationDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenResponse tokenResponse = (TokenResponse) o;
    return Objects.equals(this.accessToken, tokenResponse.accessToken) &&
        Objects.equals(this.refreshToken, tokenResponse.refreshToken) &&
        Objects.equals(this.accessTokenExpirationDate, tokenResponse.accessTokenExpirationDate) &&
        Objects.equals(this.refreshTokenExpirationDate, tokenResponse.refreshTokenExpirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, refreshToken, accessTokenExpirationDate, refreshTokenExpirationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenResponse {\n");
    
    sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
    sb.append("    refreshToken: ").append(toIndentedString(refreshToken)).append("\n");
    sb.append("    accessTokenExpirationDate: ").append(toIndentedString(accessTokenExpirationDate)).append("\n");
    sb.append("    refreshTokenExpirationDate: ").append(toIndentedString(refreshTokenExpirationDate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

