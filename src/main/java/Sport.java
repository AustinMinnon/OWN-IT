import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.WordUtils;

public class Sport {
  private String name;
  private int id;

  public Sport (String name) {
    this.name = name;
  }

  public String getName() {
    return name;
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
      this.getId() == (newSport.getId());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sports(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
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
