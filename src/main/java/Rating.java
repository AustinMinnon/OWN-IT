import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.WordUtils;

public class Rating {
  private String name;
  private int id;

  public Rating (String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Rating> all() {
    String sql = "SELECT * FROM ratings";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Rating.class);
    }
  }

  @Override
  public boolean equals(Object otherRating) {
    if (!(otherRating instanceof Rating)) {
      return false;
    } else {
      Rating newRating = (Rating) otherRating;
      return this.getName().equals(newRating.getName()) &&
      this.getId() == (newRating.getId());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO ratings(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Rating find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM ratings where id=:id";
      Rating rating = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Rating.class);
      return rating;
    }
  }
}
