import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.apache.commons.lang.WordUtils;

public class SportTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Sport.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Sport firstSport = new Sport("BMX");
    Sport secondSport = new Sport("BMX");
    assertTrue(firstSport.equals(secondSport));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Sport newSport = new Sport("BMX");
    newSport.save();
    assertTrue(Sport.all().get(0).equals(newSport));
  }

  @Test
  public void find_findsSportInDB_true() {
    Sport newSport = new Sport("BMX");
    newSport.save();
    Sport savedSport = Sport.find(newSport.getId());
    assertTrue(newSport.equals(savedSport));
  }
}
