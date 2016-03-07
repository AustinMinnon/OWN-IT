import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.WordUtils;

public class Trick {
  private int id;
  private String name;
  private int rating;
  private String date;
  private int category_id;
  private int sport_id;
  private int user_id;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDate() {
    return date;
  }

  public int getRating() {
    return rating;
  }

  public int getCategoryId() {
    return category_id;
  }

  public int getSportId() {
    return sport_id;
  }

  public int getUserId() {
    return user_id;
  }

  public Trick(String name, int rating, String date, int category_id, int sport_id, int user_id) {
    this.name = name;
    this.rating = rating;
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
            this.getRating() == newTrick.getRating() &&
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

  public void updateRating(int rating) {
    this.rating = rating;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tricks SET rating = :rating WHERE id = :id";
      con.createQuery(sql)
        .addParameter("rating", rating)
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

  public void updateUserId(int category_id) {
    this.user_id = user_id;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tricks SET user_id = :user_id WHERE id = :id";
      con.createQuery(sql)
        .addParameter("user_id", user_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateAll(String name, int rating, String date, int category_id, int sport_id, int user_id){
    updateName(name);
    updateRating(rating);
    updateDate(date);
    updateCategoryId(category_id);
    updateCategoryId(sport_id);
    updateCategoryId(user_id);

  }

  public static Trick find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tricks where id=:id";
      Trick trick = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Trick.class);
      return trick;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tricks(name, rating, date, category_id, sport_id, user_id) VALUES (:name, :rating, :date, :category_id, :sport_id, :user_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("rating", rating)
        .addParameter("date", date)
        .addParameter("category_id", category_id)
        .addParameter("sport_id", sport_id)
        .addParameter("user_id", user_id)
        .executeUpdate()
        .getKey();
    }
  }

  public void addCategory(Category category ) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO tricks (category_id) VALUES (:category_id)";
        con.createQuery(sql)
        .addParameter("category_id", this.getCategoryId())
        .executeUpdate();
      }
    }

}
