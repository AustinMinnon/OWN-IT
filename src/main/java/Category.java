import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.WordUtils;

public class Category {
  public int id;
  public String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void firstToUppercase() {
    this.name = WordUtils.capitalize(this.name.toLowerCase());
  }

  public Category(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object otherCategory){
    if (!(otherCategory  instanceof Category)) {
      return false;
    } else {
      Category newCategory = (Category) otherCategory;
      return this.getName().equals(newCategory.getName()) &&
            this.getId() == newCategory.getId();
    }
  }

  public static List<Category> all() {
    String sql = "SELECT * FROM categories";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Category.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO categories(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
    }
  }

  public void updateName(String name) {
    this.name = name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE categories SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM categories WHERE id = :id;";
      con.createQuery(deleteQuery)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public static Category find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM categories where id=:id";
      Category category = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Category.class);
      return category;
    }
  }

}
