package repositories;

import com.google.inject.ImplementedBy;
import java.util.List;
import java.util.concurrent.CompletionStage;
import models.db.Apk;

@ImplementedBy(ApkRepositoryImpl.class)
public interface ApkRepository {
  /**
   * Get all apks currently stored in the application
   *
   * @return The apks stored in the application
   */
  CompletionStage<List<Apk>> all();
}
