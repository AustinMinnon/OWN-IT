import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.apache.commons.lang.WordUtils;

public class TrickTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Trick.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfTricksAretheSame() {
    Trick firstTrick = new Trick("Kickflip", 1, "2016-05-08", 1, 1, 3);
    Trick secondTrick = new Trick("Kickflip", 1, "2016-05-08", 1, 1, 3);
    assertTrue(firstTrick.equals(secondTrick));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Trick myTrick = new Trick("Kickflip", 1, "2016-05-08", 1, 1, 3);
    myTrick.save();
    Trick savedTrick = Trick.all().get(0);
    assertTrue(savedTrick.equals(myTrick));
  }


  @Test
  public void updateName_updatesNameIntoDatabase_true() {
    Trick myTrick = new Trick("Kickflip", 1, "2016-05-08", 1, 1, 3);
    myTrick.save();
    myTrick.updateName("Ollie");
    assertEquals(myTrick.getName(), "Ollie");
  }

  @Test
  public void update_updatesALLIntoDatabase_true() {
    Trick myTrick = new Trick("Kickflip", 1, "2016-05-08", 1, 1, 3);
    myTrick.save();
    myTrick.updateAll(2, 2, "Ollie", 1, "2016-06-08");
    assertEquals(myTrick.getName(), "Ollie");
    assertEquals(myTrick.getDate(), "2016-06-08");
    assertEquals(myTrick.getRatingId(), 1);
    assertEquals(myTrick.getCategoryId(), 2);
  }

  @Test
  public void find_findTrickInDatabase_true() {
    Trick myTrick = new Trick("Kickflip", 1, "2016-05-08", 1, 1, 3);
    myTrick.save();
    Trick savedTrick = Trick.all().get(0);
    assertTrue(myTrick.equals(savedTrick));
  }

  @Test
  public void delete_deletesTrickFromDatabase() {
    Trick myTrick = new Trick("Kickflip", 1, "2016-05-08", 1, 1, 3);
    myTrick.save();
    myTrick.delete();
    assertEquals(Trick.all().size(), 0);
  }


}
