import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.apache.commons.lang.WordUtils;

public class CategoryTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
  assertEquals(Category.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Category firstCategory = new Category("BMX",1);
    Category secondCategory = new Category("BMX",1);
    assertTrue(firstCategory.equals(secondCategory));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Category myCategory = new Category("Flatground",1);
    myCategory.save();
    assertTrue(Category.all().get(0).equals(myCategory));
  }

  // @Test
  // public void find_findBandInDatabase_true() {
  //   Band myBand = new Band("The Ramones");
  //   myBand.save();
  //   Band savedBand = Band.find(myBand.getId());
  //   assertTrue(myBand.equals(savedBand));
  // }
}
