package controllers;

import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.ApkRepository;

public class HomeController extends Controller {
  private final ApkRepository apkRepository;

  @Inject
  public HomeController(ApkRepository apkRepository) {
    this.apkRepository = apkRepository;
  }

  public CompletionStage<Result> index() {
    return apkRepository.all()
        .thenApplyAsync(apks -> ok(views.html.home.render(apks)));
  }
}
