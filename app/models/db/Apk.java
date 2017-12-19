package models.db;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

@Entity(name = "apks")
@IdClass(Apk.ApkPrimaryKey.class)
public class Apk implements Serializable {
  protected static class ApkPrimaryKey implements Serializable {
    private final String applicationId;
    private final int versionCode;

    ApkPrimaryKey(String applicationId, int versionCode) {
      this.applicationId = applicationId;
      this.versionCode = versionCode;
    }

    public ApkPrimaryKey() {
      this("", 0);
    }

    public String getApplicationId() {
      return applicationId;
    }

    public int getVersionCode() {
      return versionCode;
    }

    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ApkPrimaryKey that = (ApkPrimaryKey) o;
      return versionCode == that.versionCode &&
          Objects.equals(applicationId, that.applicationId);
    }

    @Override public int hashCode() {
      return Objects.hash(applicationId, versionCode);
    }

    @Override public String toString() {
      return "ApkPrimaryKey{" +
          "applicationId='" + applicationId + '\'' +
          ", versionCode=" + versionCode +
          '}';
    }
  }

  @Id
  private final String applicationId;
  @Id
  private final int versionCode;
  @NotNull
  private final String versionName;

  public Apk(String applicationId, int versionCode, String versionName) {
    this.applicationId = applicationId;
    this.versionCode = versionCode;
    this.versionName = versionName;
  }

  public Apk() {
    this("", 0, "");
  }

  public String getApplicationId() {
    return applicationId;
  }

  public int getVersionCode() {
    return versionCode;
  }

  public String getVersionName() {
    return versionName;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Apk apk = (Apk) o;
    return versionCode == apk.versionCode &&
        Objects.equals(applicationId, apk.applicationId) &&
        Objects.equals(versionName, apk.versionName);
  }

  @Override public int hashCode() {
    return Objects.hash(applicationId, versionCode, versionName);
  }

  @Override public String toString() {
    return "Apk{" +
        "applicationId='" + applicationId + '\'' +
        ", versionCode=" + versionCode +
        ", versionName='" + versionName + '\'' +
        '}';
  }
}
