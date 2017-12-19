package controllers;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import play.mvc.Controller;
import play.mvc.Result;

public class SettingsController extends Controller {
  public CompletionStage<Result> index() {
    return CompletableFuture.completedFuture(ok(views.html.settings.render(new ArrayList<>())));
  }
}
