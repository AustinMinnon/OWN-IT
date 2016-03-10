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
      List<Trick> userTricks = Trick.getUserTricks(user.getId());
      model.put("userTricks", userTricks);
      model.put("sports", Sport.all());
      model.put("user", user);
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/create-user", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("loginUsername");
      model.put("template", "templates/create-user.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/skateboarding", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.session().attribute("userId"));
      List<Trick> userSkateTricks = Trick.getUserSkateTricks(user.getId());
      List<Trick> userSkateTricksFlat = Trick.getUserSkateFlat(user.getId());
      List<Trick> userSkateTricksFlip = Trick.getUserSkateFlip(user.getId());
      List<Trick> userSkateTricksAir = Trick.getUserSkateAir(user.getId());
      List<Trick> userSkateTricksGrind = Trick.getUserSkateGrind(user.getId());
      model.put("userSkateTricksFlat", userSkateTricksFlat);
      model.put("userSkateTricksFlip", userSkateTricksFlip);
      model.put("userSkateTricksAir", userSkateTricksAir);
      model.put("userSkateTricksGrind", userSkateTricksGrind);
      model.put("userSkateTricks", userSkateTricks);
      model.put("categories", Category.all());
      model.put("user", user);
      model.put("template", "templates/skateboarding.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/snowboarding", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.session().attribute("userId"));
      List<Trick> userSnowTricks = Trick.getUserSnowTricks(user.getId());
      List<Trick> userSnowTricksFlat = Trick.getUserSnowFlat(user.getId());
      List<Trick> userSnowTricksFlip = Trick.getUserSnowFlip(user.getId());
      List<Trick> userSnowTricksAir = Trick.getUserSnowAir(user.getId());
      List<Trick> userSnowTricksGrind = Trick.getUserSnowGrind(user.getId());
      model.put("userSnowTricksFlat", userSnowTricksFlat);
      model.put("userSnowTricksFlip", userSnowTricksFlip);
      model.put("userSnowTricksAir", userSnowTricksAir);
      model.put("userSnowTricksGrind", userSnowTricksGrind);
      model.put("userSnowTricks", userSnowTricks);
      model.put("categories", Category.all());
      model.put("user", user);
      model.put("template", "templates/snowboarding.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bmx", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.session().attribute("userId"));
      List<Trick> userBmxTricks = Trick.getUserBmxTricks(user.getId());
      List<Trick> userBmxTricksFlat = Trick.getUserBmxFlat(user.getId());
      List<Trick> userBmxTricksFlip = Trick.getUserBmxFlip(user.getId());
      List<Trick> userBmxTricksAir = Trick.getUserBmxAir(user.getId());
      List<Trick> userBmxTricksGrind = Trick.getUserBmxGrind(user.getId());
      model.put("userBmxTricksFlat", userBmxTricksFlat);
      model.put("userBmxTricksFlip", userBmxTricksFlip);
      model.put("userBmxTricksAir", userBmxTricksAir);
      model.put("userBmxTricksGrind", userBmxTricksGrind);
      model.put("userBmxTricks", userBmxTricks);
      model.put("categories", Category.all());
      model.put("user", user);
      model.put("template", "templates/bmx.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/skiing", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.session().attribute("userId"));
      List<Trick> userSkiTricks = Trick.getUserSkiTricks(user.getId());
      List<Trick> userSkiTricksFlat = Trick.getUserSkiFlat(user.getId());
      List<Trick> userSkiTricksFlip = Trick.getUserSkiFlip(user.getId());
      List<Trick> userSkiTricksAir = Trick.getUserSkiAir(user.getId());
      List<Trick> userSkiTricksGrind = Trick.getUserSkiGrind(user.getId());
      model.put("userSkiTricksFlat", userSkiTricksFlat);
      model.put("userSkiTricksFlip", userSkiTricksFlip);
      model.put("userSkiTricksAir", userSkiTricksAir);
      model.put("userSkiTricksGrind", userSkiTricksGrind);
      model.put("userSkiTricks", userSkiTricks);
      model.put("categories", Category.all());
      model.put("user", user);
      model.put("template", "templates/skiing.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/add/trick", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.session().attribute("userId"));
      List<Trick> userTricks = Trick.getUserTricks(user.getId());
      List<Category> categories = Category.all();
      List<Sport> sports = Sport.all();
      List<Rating> ratings = Rating.all();
      model.put("user", user);
      model.put("ratings", ratings);
      model.put("categories", categories);
      model.put("sports", sports);
      model.put("userTricks", userTricks);
      model.put("template", "templates/trick.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add/trick", (request, response) -> {
      User user = User.find(request.session().attribute("userId"));

      int categoryId = Integer.parseInt(request.queryParams("category_id"));
      int sportId = Integer.parseInt(request.queryParams("sport_id"));
      int trickRating = Integer.parseInt(request.queryParams("trickRating"));
      String trickName = request.queryParams("trickName");
      String trickDate = request.queryParams("trickDate");
      Category category = Category.find(1);
      Trick trick = new Trick (trickName, trickRating, trickDate, categoryId, sportId, user.getId());

      trick.save();
      response.redirect("/add/trick");
      return null;
    });

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
    response.redirect("/");
    return null;
  });

  post("/delete/trick/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    User user = User.find(request.session().attribute("userId"));
    int id = Integer.parseInt(request.queryParams("trickId"));
    Trick newTrick = Trick.find(id);
    newTrick.delete();
    model.put("user", user);
    model.put("ratings", Rating.all());
    model.put("categories", Category.all());
    model.put("tricks", Trick.all());
    model.put("sports", Sport.all());
    model.put("template", "templates/trick.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("skateboarding/delete/trick/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.session().attribute("userId"));
      int id = Integer.parseInt(request.queryParams("trickId"));
      Trick newTrick = Trick.find(id);
      newTrick.delete();
      model.put("user", user);
      model.put("ratings", Rating.all());
      model.put("categories", Category.all());
      model.put("tricks", Trick.all());
      model.put("sports", Sport.all());
      model.put("template", "templates/trick.vtl");
      response.redirect("/skateboarding");
      return null;
    });

    post("snowboarding/delete/trick/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.session().attribute("userId"));
      int id = Integer.parseInt(request.queryParams("trickId"));
      Trick newTrick = Trick.find(id);
      newTrick.delete();
      model.put("user", user);
      model.put("ratings", Rating.all());
      model.put("categories", Category.all());
      model.put("tricks", Trick.all());
      model.put("sports", Sport.all());
      model.put("template", "templates/trick.vtl");
      response.redirect("/snowboarding");
      return null;
    });

    post("bmx/delete/trick/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.session().attribute("userId"));
      int id = Integer.parseInt(request.queryParams("trickId"));
      Trick newTrick = Trick.find(id);
      newTrick.delete();
      model.put("user", user);
      model.put("ratings", Rating.all());
      model.put("categories", Category.all());
      model.put("tricks", Trick.all());
      model.put("sports", Sport.all());
      model.put("template", "templates/trick.vtl");
      response.redirect("/skiing");
      return null;
    });

    post("skiing/delete/trick/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.session().attribute("userId"));
      int id = Integer.parseInt(request.queryParams("trickId"));
      Trick newTrick = Trick.find(id);
      newTrick.delete();
      model.put("user", user);
      model.put("ratings", Rating.all());
      model.put("categories", Category.all());
      model.put("tricks", Trick.all());
      model.put("sports", Sport.all());
      model.put("template", "templates/trick.vtl");
      response.redirect("/skiing");
      return null;
    });

  get("update/trick/:id", (request,response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    User user = User.find(request.session().attribute("userId"));
    int id = Integer.parseInt(request.queryParams("trickId"));
    Trick currentTrick = Trick.find(id);
    model.put("user", user);
    model.put("trick", currentTrick);
    model.put("ratings", Rating.all());
    model.put("categories", Category.all());
    model.put("sports", Sport.all());
    model.put("template", "templates/updateTrick.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/update/trick/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    User user = User.find(request.session().attribute("userId"));
     List<Trick> userTricks = Trick.getUserTricks(user.getId());
    int trickId = Integer.parseInt(request.queryParams("trickId"));
    int categoryId = Integer.parseInt(request.queryParams("category_id"));
    int sportId = Integer.parseInt(request.queryParams("sport_id"));
    int trickRating = Integer.parseInt(request.queryParams("trickRating"));
    String trickName = request.queryParams("trickName");
    String trickDate = request.queryParams("trickDate");
    Trick myTrick = Trick.find(trickId);
    myTrick.updateAll(sportId, categoryId, trickName, trickRating, trickDate);
    model.put("user", user);
    model.put("ratings", Rating.all());
    model.put("categories", Category.all());
    model.put("userTricks", userTricks);
    model.put("sports", Sport.all());
    response.redirect("/skateboarding");
    return null;
    });

    // post("/update/trick/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   User user = User.find(request.session().attribute("userId"));
    //   List<Trick> userTricks = Trick.getUserTricks(user.getId());
    //   int trickId = Integer.parseInt(request.queryParams("trickId"));
    //   int categoryId = Integer.parseInt(request.queryParams("category_id"));
    //   int sportId = Integer.parseInt(request.queryParams("sport_id"));
    //   int trickRating = Integer.parseInt(request.queryParams("trickRating"));
    //   String trickName = request.queryParams("trickName");
    //   String trickDate = request.queryParams("trickDate");
    //   Trick myTrick = Trick.find(trickId);
    //   myTrick.updateAll(sportId, categoryId, trickName, trickRating, trickDate);
    //   // model.put("user", user);
    //   // model.put("ratings", Rating.all());
    //   // model.put("categories", Category.all());
    //   // model.put("userTricks", userTricks);
    //   // model.put("sports", Sport.all());
    //   // model.put("template", "templates/trick.vtl");
    //   response.redirect("/skateboarding");
    //   return null;
    // });
  }
}
