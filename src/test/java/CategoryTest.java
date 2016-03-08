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
    Category firstCategory = new Category("BMX");
    Category secondCategory = new Category("BMX");
    assertTrue(firstCategory.equals(secondCategory));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Category myCategory = new Category("Flatground");
    myCategory.save();
    assertTrue(Category.all().get(0).equals(myCategory));
  }

  @Test
  public void update_updatesNameIntoDatabase_true() {
    Category myCategory = new Category("Flatground");
    myCategory.save();
    myCategory.updateName("Jumps");
    assertEquals(myCategory.getName(), "Jumps");
  }

  @Test
  public void find_findCategoryInDatabase_true() {
    Category myCategory = new Category("Flatground");
    myCategory.save();
    Category savedCategory = Category.find(myCategory.getId());
    assertTrue(myCategory.equals(savedCategory));
  }

  @Test
  public void delete_deletesCategoryFromDatabase() {
    Category myCategory = new Category("Flatground");
    myCategory.save();
    myCategory.delete();
    assertEquals(Category.all().size(), 0);
  }
}
