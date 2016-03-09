import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class User {
  private String name;
  private int id;
  private boolean mDuplicate;

  public User (String name) {
    this.name = name;
    mDuplicate = false;
    for (User user : User.all()) {
      if (this.name.equals(user.getName())) {
        mDuplicate = true;
      }
    }
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public boolean isDuplicate() {
    return mDuplicate;
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

  public List<Trick> getTricks(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT tricks.id, tricks.name FROM tricks JOIN users_tricks ON (tricks.id = users_tricks.trick_id) JOIN users ON(users_tricks.user_id = users.id) WHERE users.id = :user_id;";
      List<Trick> tricks = con.createQuery(sql)
        .addParameter("user_id", this.getId())
        .executeAndFetch(Trick.class);
        return tricks;
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

  public static User findByUserName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users where name=:username";
      User user = con.createQuery(sql)
        .addParameter("username", name)
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
