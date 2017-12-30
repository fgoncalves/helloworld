package repositories;

import di.DatabaseExecutor;
import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import models.db.App;
import play.db.jpa.JPAApi;
import scala.concurrent.ExecutionContextExecutor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class AppRepositoryImpl implements AppRepository {
  @Nonnull
  private final JPAApi jpaApi;
  @Nonnull
  private final ExecutionContextExecutor executionContext;

  public AppRepositoryImpl(
      @Nonnull JPAApi jpaApi,
      @Nonnull @DatabaseExecutor ExecutionContextExecutor executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override public CompletionStage<App> get(@Nullable String appId) {
    return supplyAsync(() -> jpaApi.withTransaction(() -> {
          App app = jpaApi.em().find(App.class, appId);
          return app == null ? App.NONE : app;
        }
    ), executionContext);
  }

  @Override public CompletionStage<List<App>> all() {
    return null;
  }

  @Override public CompletionStage<App> createOrUpdate(@Nonnull App app) {
    return null;
  }

  @Override public CompletionStage<Boolean> delete(@Nonnull String appId) {
    return null;
  }
}
