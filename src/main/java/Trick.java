import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.WordUtils;

public class Trick {
  private int id;
  private String name;
  private int rating_id;
  private String date;
  private int category_id;
  private int sport_id;
  private int user_id;

  public int getId() {
    return id;
  }
  public int getUserId() {
    return user_id;
  }

  public String getName() {
    return name;
  }

  public String getDate() {
    return date;
  }

  public int getRatingId() {
    return rating_id;
  }

  public int getCategoryId() {
    return category_id;
  }

  public int getSportId() {
    return sport_id;
  }

  public String getRatingName() {
    if (rating_id == 2) {
      return "Clean";
    } else {
      return "Sloppy";
    }
  }

  public String getSportName() {
    if (sport_id == 1) {
      return "Skateboarding";
    } else if (sport_id == 2) {
      return "Snowboarding";
    } else if (sport_id == 3) {
      return "BMX";
    } else {
      return "Skiing";
    }
  }

  public String getCategoryName() {
    if (category_id == 1) {
      return "Flatground";
    } else if (category_id == 2) {
      return "Flip Tricks";
    } else if (category_id == 3) {
      return "Air";
    } else {
      return "Grind";
    }
  }


  public static List<Trick> getUserTricks(int id) {
    String sql = "SELECT * FROM tricks WHERE user_id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSkateTricks(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 1 AND user_id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSnowTricks(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 2 AND user_id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserBmxTricks(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 3 AND user_id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSkiTricks(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 4 AND user_id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }


  public static List<Trick> getUserSkateFlat(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 1 AND user_id = :id AND category_id = 1";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSnowFlat(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 2 AND user_id = :id AND category_id = 1";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserBmxFlat(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 3 AND user_id = :id AND category_id = 1";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSkiFlat(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 4 AND user_id = :id AND category_id = 1";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSkateFlip(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 1 AND user_id = :id AND category_id = 2";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSnowFlip(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 2 AND user_id = :id AND category_id = 2";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserBmxFlip(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 3 AND user_id = :id AND category_id = 2";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSkiFlip(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 4 AND user_id = :id AND category_id = 2";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSkateAir(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 1 AND user_id = :id AND category_id = 3";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSnowAir(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 2 AND user_id = :id AND category_id = 3";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserBmxAir(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 3 AND user_id = :id AND category_id = 3";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSkiAir(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 4 AND user_id = :id AND category_id = 3";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSkateGrind(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 1 AND user_id = :id AND category_id = 4";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSnowGrind(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 2 AND user_id = :id AND category_id = 4";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserBmxGrind(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 3 AND user_id = :id AND category_id = 4";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public static List<Trick> getUserSkiGrind(int id) {
    String sql = "SELECT * FROM tricks WHERE sport_id = 4 AND user_id = :id AND category_id = 4";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Trick.class);
    }
  }

  public Trick(String name, int rating_id, String date, int category_id, int sport_id, int user_id) {
    this.name = name;
    this.rating_id = rating_id;
    this.date = date;
    this.category_id = category_id;
    this.sport_id = sport_id;
    this.user_id = user_id;
  }

  public void firstToUppercase() {
    this.name = WordUtils.capitalize(this.name.toLowerCase());
  }

  @Override
  public boolean equals(Object otherTrick){
    if (!(otherTrick  instanceof Trick)) {
      return false;
    } else {
      Trick newTrick = (Trick) otherTrick;
      return this.getName().equals(newTrick.getName()) &&
            this.getId() == newTrick.getId() &&
            this.getCategoryId() == newTrick.getCategoryId() &&
            this.getDate().equals(newTrick.getDate()) &&
            this.getRatingId() == newTrick.getRatingId() &&
            this.getSportId() == newTrick.getSportId() &&
            this.getUserId() == newTrick.getUserId();
    }
  }

  public static List<Trick> all() {
    String sql = "SELECT * FROM tricks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Trick.class);
    }
  }


  public void updateName(String name) {
    this.name = name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tricks SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateRatingId(int rating_id) {
    this.rating_id = rating_id;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tricks SET rating_id = :rating_id WHERE id = :id";
      con.createQuery(sql)
        .addParameter("rating_id", rating_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateDate(String date) {
    this.date = date;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tricks SET date = :date WHERE id = :id";
      con.createQuery(sql)
        .addParameter("date", date)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateCategoryId(int category_id) {
    this.category_id = category_id;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tricks SET category_id = :category_id WHERE id = :id";
      con.createQuery(sql)
        .addParameter("category_id", category_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateSportId(int sport_id) {
    this.sport_id = sport_id;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tricks SET sport_id = :sport_id WHERE id = :id";
      con.createQuery(sql)
        .addParameter("sport_id", sport_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }


  public void updateAll(int sport_id, int category_id, String name, int rating_id, String date){
    updateName(name);
    updateRatingId(rating_id);
    updateDate(date);
    updateCategoryId(category_id);
    updateSportId(sport_id);
  }

  // public static Trick find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM tricks where id=:id";
  //     Trick trick = con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Trick.class);
  //     return trick;
  //   }
  //
  // }

  //CREATE
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tricks(name, rating_id, date, category_id, sport_id, user_id) VALUES (:name, :rating_id, :date, :category_id, :sport_id, :user_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("rating_id", rating_id)
        .addParameter("date", date)
        .addParameter("category_id", category_id)
        .addParameter("sport_id", sport_id)
        .addParameter("user_id", user_id)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static Trick find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tricks where id=:id";
      Trick trick = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Trick.class);
      return trick;
    }
  }

  //DELETE
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tricks WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
      // String deleteQuery = "DELETE FROM users_tricks WHERE trick_id = :id;";
      // con.createQuery(deleteQuery)
      // .addParameter("id", id)
      // .executeUpdate();
    }
  }
}
