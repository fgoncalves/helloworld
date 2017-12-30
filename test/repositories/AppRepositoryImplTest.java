package repositories;

import java.util.function.Supplier;
import javax.persistence.EntityManager;
import models.db.App;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import play.db.jpa.JPAApi;
import utils.SerialExectuionContextExecutor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppRepositoryImplTest {
  @Mock JPAApi jpaApi;
  @Mock EntityManager entityManager;

  private AppRepositoryImpl appRepository;

  @Before
  public void setUp() {
    doAnswer(invocation -> {
      Supplier supplier = invocation.getArgument(0);
      return supplier.get();
    }).when(jpaApi).withTransaction(any(Supplier.class));
    when(jpaApi.em()).thenReturn(entityManager);

    appRepository = new AppRepositoryImpl(jpaApi, SerialExectuionContextExecutor.executor());
  }

  @Test
  public void get_shouldReturnTheCorrectAppWhenItExists() {
    App expected = new App("foo.bar", "Foobar", "description", "");
    when(entityManager.find(App.class, "foo.bar")).thenReturn(expected);

    App result = appRepository.get("foo.bar")
        .toCompletableFuture()
        .join();

    assertThat(result).isEqualTo(expected);
  }

  @Test
  public void get_shouldReturnNoneWhenAppDoesNotExist() {
    //noinspection unchecked
    when(entityManager.find(any(Class.class), anyString())).thenReturn(App.NONE);

    App result = appRepository.get("foo.bar")
        .toCompletableFuture()
        .join();

    assertThat(result).isEqualTo(App.NONE);
  }
}