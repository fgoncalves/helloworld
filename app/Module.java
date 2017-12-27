import com.google.inject.AbstractModule;
import di.DatabaseExecutor;
import models.db.DatabaseExecutionContext;
import scala.concurrent.ExecutionContextExecutor;

public class Module extends AbstractModule {

  @Override protected void configure() {
    bind(ExecutionContextExecutor.class)
        .annotatedWith(DatabaseExecutor.class)
        .to(DatabaseExecutionContext.class);
  }
}
