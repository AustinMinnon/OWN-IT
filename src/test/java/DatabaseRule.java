import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/own_it_test", null, null);
  }

    protected void after() {
      try(Connection con = DB.sql2o.open()) {
        String deleteUsersQuery = "DELETE FROM users *;";
        String deleteSportsQuery = "DELETE FROM sports *;";
        String deleteCategoriesQuery = "DELETE FROM categories *;";
        String deleteTricksQuery = "DELETE FROM tricks *;";

        con.createQuery(deleteUsersQuery).executeUpdate();
        con.createQuery(deleteSportsQuery).executeUpdate();
        con.createQuery(deleteCategoriesQuery).executeUpdate();
        con.createQuery(deleteTricksQuery).executeUpdate();

      }
   }
}
