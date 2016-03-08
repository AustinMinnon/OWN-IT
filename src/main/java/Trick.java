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

  public int getId() {
    return id;
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

  public Trick(String name, int rating_id, String date, int category_id, int sport_id) {
    this.name = name;
    this.rating_id = rating_id;
    this.date = date;
    this.category_id = category_id;
    this.sport_id = sport_id;
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
            this.getSportId() == newTrick.getSportId();
    }
  }

  public static List<Trick> all() {
    String sql = "SELECT * FROM tricks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Trick.class);
    }
  }

  // public static List<Trick> userTricks() {
  //   String sql = "SELECT * FROM tricks JOIN users ON (users.id = tricks.user_id) WHERE tricks_user.id=:id";
  //   try(Connection con = DB.sql2o.open()) {
  //     return con.createQuery(sql).executeAndFetch(Trick.class);
  //   }
  // }

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

  public void updateAll(String name, int rating_id, String date, int category_id, int sport_id){
    updateName(name);
    updateRatingId(rating_id);
    updateDate(date);
    updateCategoryId(category_id);
    updateCategoryId(sport_id);
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
      String sql = "INSERT INTO tricks(name, rating_id, date, category_id, sport_id) VALUES (:name, :rating_id, :date, :category_id, :sport_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("rating_id", rating_id)
        .addParameter("date", date)
        .addParameter("category_id", category_id)
        .addParameter("sport_id", sport_id)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM tricks WHERE id = :id;";
      con.createQuery(deleteQuery)
      .addParameter("id", id)
      .executeUpdate();
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
