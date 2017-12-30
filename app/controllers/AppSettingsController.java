package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.annotation.Nullable;
import models.db.App;
import play.mvc.Controller;
import play.mvc.Result;

public class AppSettingsController extends Controller {
  public CompletionStage<Result> appSettings(@Nullable String appId) {
    return CompletableFuture.completedFuture(null)
        .thenApplyAsync(p -> ok(views.html.app_settings.render(App.NONE)));
  }

  public CompletionStage<Result> newApp() {
    return appSettings(null);
  }
}
