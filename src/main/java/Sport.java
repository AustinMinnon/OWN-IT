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

 // public static List <Sport>




}
