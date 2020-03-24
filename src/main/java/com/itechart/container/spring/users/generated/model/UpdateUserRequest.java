package com.itechart.container.spring.users.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateUserRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-03-24T14:09:10.456767+03:00[Europe/Minsk]")

public class UpdateUserRequest   {
  @JsonProperty("newFirstName")
  private String newFirstName;

  @JsonProperty("newLastName")
  private String newLastName;

  @JsonProperty("newPhone")
  private String newPhone;

  public UpdateUserRequest newFirstName(String newFirstName) {
    this.newFirstName = newFirstName;
    return this;
  }

  /**
   * Get newFirstName
   * @return newFirstName
  */
  @ApiModelProperty(value = "")


  public String getNewFirstName() {
    return newFirstName;
  }

  public void setNewFirstName(String newFirstName) {
    this.newFirstName = newFirstName;
  }

  public UpdateUserRequest newLastName(String newLastName) {
    this.newLastName = newLastName;
    return this;
  }

  /**
   * Get newLastName
   * @return newLastName
  */
  @ApiModelProperty(value = "")


  public String getNewLastName() {
    return newLastName;
  }

  public void setNewLastName(String newLastName) {
    this.newLastName = newLastName;
  }

  public UpdateUserRequest newPhone(String newPhone) {
    this.newPhone = newPhone;
    return this;
  }

  /**
   * Get newPhone
   * @return newPhone
  */
  @ApiModelProperty(example = "+753 92 321 54 76", value = "")

@Pattern(regexp="^+[0-9 ]{5,20}$") 
  public String getNewPhone() {
    return newPhone;
  }

  public void setNewPhone(String newPhone) {
    this.newPhone = newPhone;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateUserRequest updateUserRequest = (UpdateUserRequest) o;
    return Objects.equals(this.newFirstName, updateUserRequest.newFirstName) &&
        Objects.equals(this.newLastName, updateUserRequest.newLastName) &&
        Objects.equals(this.newPhone, updateUserRequest.newPhone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newFirstName, newLastName, newPhone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateUserRequest {\n");
    
    sb.append("    newFirstName: ").append(toIndentedString(newFirstName)).append("\n");
    sb.append("    newLastName: ").append(toIndentedString(newLastName)).append("\n");
    sb.append("    newPhone: ").append(toIndentedString(newPhone)).append("\n");
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

