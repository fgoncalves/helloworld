package repositories;

import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.inject.Singleton;
import models.db.Apk;
import models.db.DatabaseExecutionContext;
import play.db.jpa.JPAApi;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class ApkRepositoryImpl implements ApkRepository {
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject ApkRepositoryImpl(
      JPAApi jpaApi,
      DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override public CompletionStage<List<Apk>> all() {
    return supplyAsync(() -> jpaApi.withTransaction(() -> jpaApi.em()
        .createQuery("FROM apks", Apk.class)
        .getResultList()), executionContext);
  }
}
