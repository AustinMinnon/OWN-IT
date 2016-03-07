import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.WordUtils;

public class Trick {
  public int id;
  public String name;
  public String date;
  public int category_id;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getCategoryId() {
    return category_id;
  }

  public String getDate() {
    return date;
  }

  public Trick(String name, String date, int category_id) {
    this.name = name;
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
            this.getDate() == newTrick.getDate();
    }
  }
}
