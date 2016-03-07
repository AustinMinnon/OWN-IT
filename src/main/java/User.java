import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class User {
  private String name;
  private int id;

  public User (String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherUser){
    if (!(otherUser instanceof User)) {
      return false;
    } else {
      User newUser = (User) otherUser;
      return this.getName().equals(newUser.getName()) &&
             this.getId() == newUser.getId();
    }
  }

  public static List<User> all() {
    String sql = "SELECT * FROM users";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  //CREATE
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO users(name) VALUES (:name)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .executeUpdate()
      .getKey();
    }
  }

  //READ
  public static User find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users where id=:id";
      User user = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(User.class);
      return user;
    }
  }

  //UPDATE
  public void update(String username) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE users SET username = :username WHERE id = :id";
    con.createQuery(sql)
      .addParameter("username", username)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  //DELETE
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM users WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();

    String deleteQuery = "DELETE FROM sports WHERE user_id = :id";
      con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
