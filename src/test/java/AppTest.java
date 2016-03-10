import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Own It!");
  }

  @Test
  public void UserIsCreatedTest() {
    goTo("http://localhost:4567/create-user");
    fill("#username").with("Jimbob");
    submit(".btn");
    assertThat(pageSource()).contains("Enter Your Username");
  }

  @Test
  public void userIsRegistered() {
    goTo("http://localhost:4567/create-user");
    fill("#username").with("JoeBob");
    submit(".btn");
    fill("#loginUsername").with("JoeBob");
    submit(".btn");
    assertThat(pageSource()).contains("JoeBob");
  }
}
