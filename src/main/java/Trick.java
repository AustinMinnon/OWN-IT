import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.WordUtils;

public class Trick {
  public int id;
  public String name;
  public int rating;
  public String date;
  public int category_id;

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

  public Trick(String name, int rating, String date, int category_id) {
    this.name = name;
    this.rating = rating;
    this.date = date;
    this.category_id = category_id;
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
            this.getRating() == newTrick.getRating();
    }
  }

  public static List<Trick> all() {
    String sql = "SELECT * FROM tricks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Trick.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tricks(name, rating, date, category_id) VALUES (:name, :rating, :date, :category_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("rating", rating)
        .addParameter("date", date)
        .addParameter("category_id", category_id)
        .executeUpdate()
        .getKey();
    }
  }

}
