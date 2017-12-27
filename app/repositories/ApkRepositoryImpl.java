package repositories;

import di.DatabaseExecutor;
import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.inject.Singleton;
import models.db.Apk;
import play.db.jpa.JPAApi;
import scala.concurrent.ExecutionContextExecutor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class ApkRepositoryImpl implements ApkRepository {
  private final JPAApi jpaApi;
  private final ExecutionContextExecutor executionContext;

  @Inject ApkRepositoryImpl(
      JPAApi jpaApi,
      @DatabaseExecutor ExecutionContextExecutor executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override public CompletionStage<List<Apk>> all() {
    return supplyAsync(() -> jpaApi.withTransaction(() -> jpaApi.em()
        .createQuery("FROM apks", Apk.class)
        .getResultList()), executionContext);
  }
}
