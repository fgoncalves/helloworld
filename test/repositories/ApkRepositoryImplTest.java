package repositories;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.db.Apk;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import play.db.jpa.JPAApi;
import utils.SerialExectuionContextExecutor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApkRepositoryImplTest {
  @Mock JPAApi jpaApi;
  @Mock EntityManager entityManager;

  private ApkRepositoryImpl apkRepository;

  @Before
  public void setUp() {
    doAnswer(invocation -> {
      Supplier supplier = invocation.getArgument(0);
      return supplier.get();
    }).when(jpaApi).withTransaction(any(Supplier.class));
    when(jpaApi.em()).thenReturn(entityManager);

    apkRepository = new ApkRepositoryImpl(jpaApi, SerialExectuionContextExecutor.executor());
  }

  @Test
  public void all_shouldQueryForAllApks() throws ExecutionException, InterruptedException {
    List<Apk> expected = Collections.singletonList(new Apk("foo", 1, "bar"));
    TypedQuery<Apk> query = mock(TypedQuery.class);
    when(query.getResultList()).thenReturn(expected);
    when(entityManager.createQuery("FROM apks", Apk.class))
        .thenReturn(query);

    List<Apk> result = apkRepository.all()
        .toCompletableFuture()
        .join();

    assertThat(result).containsOnly(new Apk("foo", 1, "bar"));
  }
}