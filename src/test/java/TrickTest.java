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
    Trick firstTrick = new Trick("Kickflip", 1, "2016-05-08", 1);
    Trick secondTrick = new Trick("Kickflip", 1, "2016-05-08", 1);
    assertTrue(firstTrick.equals(secondTrick));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Trick myTrick = new Trick("Kickflip", 1, "2016-05-08", 1);
    myTrick.save();
    Trick savedTrick = Trick.all().get(0);
    assertTrue(savedTrick.equals(myTrick));
  }
}
