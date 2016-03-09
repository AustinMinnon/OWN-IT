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

    get("/add/trick", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Category> categories = Category.all();
      List<Trick> tricks = Trick.all();
      List<Sport> sports = Sport.all();
      List<Rating> ratings = Rating.all();
      model.put("ratings", ratings);
      model.put("categories", categories);
      model.put("tricks", tricks);
      model.put("sports", sports);
      model.put("template", "templates/trick.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add/trick", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int categoryId = Integer.parseInt(request.queryParams("category_id"));
      int sportId = Integer.parseInt(request.queryParams("sport_id"));
      int trickRating = Integer.parseInt(request.queryParams("trickRating"));
      String trickName = request.queryParams("trickName");
      String trickDate = request.queryParams("trickDate");
      Category category = Category.find(1);
      Trick trick = new Trick (trickName, trickRating, trickDate, categoryId, sportId);
      boolean duplicateTrickRequested = trick.isDuplicate();
      if(!(duplicateTrickRequested)) {
      trick.save();
      model.put("template", "templates/trick.vtl");
      } else {
      model.put("duplicatetrickrequested", duplicateTrickRequested);
      model.put("template", "templates/trick.vtl");
    }
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get(":user_id/bmx/", (request, response) -> {
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

  //DELETE TRICK
  post("/delete/trick/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int id = Integer.parseInt(request.queryParams("trickId"));
    Trick.delete(id);
    model.put("ratings", Rating.all());
    model.put("categories", Category.all());
    model.put("tricks", Trick.all());
    model.put("sports", Sport.all());
    model.put("template", "templates/trick.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  // get("/updateSport/trick/:id", (request,response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   List<Category> categories = Category.all();
  //   List<Trick> tricks = Trick.all();
  //   List<Sport> sports = Sport.all();
  //   List<Rating> ratings = Rating.all();
  //   model.put("ratings", ratings);
  //   model.put("categories", categories);
  //   model.put("tricks", tricks);
  //   model.put("sports", sports);
  //   model.put("template", "templates/update.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());

  get("update/trick/:id", (request,response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int id = Integer.parseInt(request.queryParams("trickId"));
    Trick currentTrick = Trick.find(id);
    model.put("trick", currentTrick);
    model.put("ratings", Rating.all());
    model.put("categories", Category.all());
    model.put("sports", Sport.all());
    model.put("template", "templates/updateTrick.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/update/trick/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int trickId = Integer.parseInt(request.queryParams("trickId"));
    int categoryId = Integer.parseInt(request.queryParams("category_id"));
    int sportId = Integer.parseInt(request.queryParams("sport_id"));
    int trickRating = Integer.parseInt(request.queryParams("trickRating"));
    String trickName = request.queryParams("trickName");
    String trickDate = request.queryParams("trickDate");
    Trick myTrick = Trick.find(trickId);
    myTrick.updateAll(sportId, categoryId, trickName, trickRating, trickDate);
    model.put("ratings", Rating.all());
    model.put("categories", Category.all());
    model.put("tricks", Trick.all());
    model.put("sports", Sport.all());
    model.put("template", "templates/trick.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
