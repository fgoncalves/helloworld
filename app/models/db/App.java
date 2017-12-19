package models.db;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "apps")
public class App {
  @Id
  private final String applicationId;
  @NotNull
  private final String name;
  private final String description;
  private final String iconUrl;

  public App(String applicationId, String name, String description, String iconUrl) {
    this.applicationId = applicationId;
    this.name = name;
    this.description = description;
    this.iconUrl = iconUrl;
  }

  public App() {
    this("", "", "", "");
  }

  public String getApplicationId() {
    return applicationId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    App app = (App) o;
    return Objects.equals(applicationId, app.applicationId) &&
        Objects.equals(name, app.name) &&
        Objects.equals(description, app.description) &&
        Objects.equals(iconUrl, app.iconUrl);
  }

  @Override public int hashCode() {

    return Objects.hash(applicationId, name, description, iconUrl);
  }

  @Override public String toString() {
    return "App{" +
        "applicationId='" + applicationId + '\'' +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", iconUrl='" + iconUrl + '\'' +
        '}';
  }
}
