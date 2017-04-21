package com.github.tothcs.model.network;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class Note   {
  
  @SerializedName("id")
  private Long id = null;
  
  @SerializedName("title")
  private String title = null;
  
  @SerializedName("description")
  private String description = null;
  

public enum CategoryEnum {
  @SerializedName("STUDY")
  STUDY("STUDY"),

  @SerializedName("WORK")
  WORK("WORK"),

  @SerializedName("PERSONAL")
  PERSONAL("PERSONAL");

  private String value;

  CategoryEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  @SerializedName("category")
  private CategoryEnum category = null;
  

public enum PriorityEnum {
  @SerializedName("LOW")
  LOW("LOW"),

  @SerializedName("NORMAL")
  NORMAL("NORMAL"),

  @SerializedName("HIGH")
  HIGH("HIGH");

  private String value;

  PriorityEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  @SerializedName("priority")
  private PriorityEnum priority = null;
  

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public CategoryEnum getCategory() {
    return category;
  }
  public void setCategory(CategoryEnum category) {
    this.category = category;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public PriorityEnum getPriority() {
    return priority;
  }
  public void setPriority(PriorityEnum priority) {
    this.priority = priority;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Note note = (Note) o;
    return Objects.equals(id, note.id) &&
        Objects.equals(title, note.title) &&
        Objects.equals(description, note.description) &&
        Objects.equals(category, note.category) &&
        Objects.equals(priority, note.priority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, category, priority);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Note {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
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
