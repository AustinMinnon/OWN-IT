import java.util.HashMap;
import java.util.List;
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

    get("/add/skate", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Trick> tricks = Trick.all();
      model.put("tricks", tricks);
      model.put("template", "templates/tricks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/bmx/:user_id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int user_id = Integer.parseInt(request.params(":user_id"));
    //   User user = User.find(user_id);
    //   int trick_id = Integer.parseInt(request.queryParams("trickId"));
    //   Trick trick = Trick.find(trick_id);
    //   model.put("user", user);
    //   model.put("tricks", Trick.all());
    //   model.put("trick", trick);
    //   model.put("userTricks", trick.userTricks());
    //   model.put("template", "templates/bmx.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("username");
      User newUser = new User(username);
      boolean duplicateUserRequested = newUser.isDuplicate();
      String name = newUser.getName();
      if(!(duplicateUserRequested)) {
        newUser.save();
        model.put("template", "templates/home.vtl");
      } else {
      model.put("duplicateuserrequested", duplicateUserRequested);
      model.put("template", "templates/index.vtl");
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
    response.redirect("/");
    return null;
  });

  }
}
