package repositories;

import com.google.inject.ImplementedBy;
import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import models.db.App;

@ImplementedBy(AppRepositoryImpl.class)
public interface AppRepository {
  /**
   * Get the app identified by the given app id. If none is found or if the parameter is
   * null, then return {@link App#NONE}.
   *
   * @param appId App id
   * @return The app identified by the provided id or {@link App#NONE} if none is found
   */
  CompletionStage<App> get(@Nullable String appId);

  /**
   * Get all the apps
   *
   * @return all the apps
   */
  CompletionStage<List<App>> all();

  /**
   * Create or update the given app. The app id must be set
   *
   * @param app The app to create or update
   * @return The created or updated app
   */
  CompletionStage<App> createOrUpdate(@Nonnull App app);

  /**
   * Delete the app with the given id
   *
   * @param appId The app id to delete
   * @return True if there was an app deleted. False otherwise
   */
  CompletionStage<Boolean> delete(@Nonnull String appId);
}
