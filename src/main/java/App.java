import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String [] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String,Object>model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/home", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("loginUsername");
      User user = User.find(request.session().attribute("userId"));
      model.put("user", user);
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("username");
      User newUser = new User(username);
      boolean duplicateUserRequested = newUser.isDuplicate();
      String name = newUser.getName();
      if(!(duplicateUserRequested)) {
        newUser.save();
        model.put("template", "templates/index.vtl");
      } else {
      model.put("duplicateuserrequested", duplicateUserRequested);
      model.put("template", "templates/create-user.vtl");
      }
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  post("/home", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String username = request.queryParams("loginUsername");
    User user = User.findByUserName(username);
    if (user != null) {
      if (user.getName().equals(username)) {
        request.session().attribute("userId", null);
        request.session().attribute("userId", user.getId());
        response.redirect("/home");
        return null;
      }
    }
    response.redirect("/home");
    return null;
  });

  }
}
