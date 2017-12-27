package utils;

import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutor;

public class SerialExectuionContextExecutor implements ExecutionContextExecutor {
  private static class Holder {
    static final SerialExectuionContextExecutor INSTANCE = new SerialExectuionContextExecutor();
  }

  private SerialExectuionContextExecutor() {
  }

  public static ExecutionContextExecutor executor() {
    return Holder.INSTANCE;
  }

  @Override public ExecutionContext prepare() {
    return this;
  }

  @Override public void execute(Runnable runnable) {
    runnable.run();
  }

  @Override public void reportFailure(Throwable cause) {
    throw new RuntimeException("Cannot schedule given runnable", cause);
  }
}
