import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.WordUtils;

public class Sport {
  private String name;
  private int user_id;
  private int id;

  public Sport (String name, int user_id) {
    this.name = name;
    this.user_id = user_id;
  }

  public String getName() {
    return name;
  }

  public int getUserId() {
    return user_id;
  }

  public int getId() {
    return id;
  }

  public static List<Sport> all() {
    String sql = "SELECT * FROM sports";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Sport.class);
    }
  }

  @Override
  public boolean equals(Object otherSport) {
    if (!(otherSport instanceof Sport)) {
      return false;
    } else {
      Sport newSport = (Sport) otherSport;
      return this.getName().equals(newSport.getName()) &&
      this.getId() == (newSport.getId()) &&
      this.getUserId() == (newSport.getUserId());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sports(name, user_id) VALUES (:name, :user_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("user_id", this.user_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Sport find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sports where id=:id";
      Sport sport = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Sport.class);
      return sport;
    }
  }

}
