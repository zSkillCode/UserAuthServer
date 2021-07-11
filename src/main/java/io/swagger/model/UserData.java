package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-11T10:50:00.847Z[GMT]")


public class UserData   {
  @JsonProperty("product")
  private String product = null;

  @JsonProperty("user_id")
  private String userId = null;

  @JsonProperty("discord_id")
  private String discordId = null;

  public UserData product(String product) {
    this.product = product;
    return this;
  }

  /**
   * Get product
   * @return product
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public UserData userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public UserData discordId(String discordId) {
    this.discordId = discordId;
    return this;
  }

  /**
   * Get discordId
   * @return discordId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getDiscordId() {
    return discordId;
  }

  public void setDiscordId(String discordId) {
    this.discordId = discordId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserData userData = (UserData) o;
    return Objects.equals(this.product, userData.product) &&
        Objects.equals(this.userId, userData.userId) &&
        Objects.equals(this.discordId, userData.discordId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, userId, discordId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserData {\n");
    
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    discordId: ").append(toIndentedString(discordId)).append("\n");
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
