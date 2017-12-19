package repositories;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.db.Apk;
import models.db.DatabaseExecutionContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import play.db.jpa.JPAApi;

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
      Runnable runnable = invocation.getArgument(0);
      runnable.run();
      return null;
    }).when(jpaApi).withTransaction(any(Runnable.class));
    when(jpaApi.em()).thenReturn(entityManager);

    apkRepository = new ApkRepositoryImpl(jpaApi, Exec);
  }

  @Test
  public void all_shouldQueryForAllApks() {
    List<Apk> expected = Collections.singletonList(new Apk("foo", 1, "bar"));
    Query query = mock(Query.class);
    when(query.getResultList()).thenReturn(expected);
    when(entityManager.createQuery("SELECT * FROM apks"))
        .thenReturn(query);

    apkRepository.all()
        .test()
        .assertValue(expected)
        .assertNoErrors()
        .assertComplete();
  }
}