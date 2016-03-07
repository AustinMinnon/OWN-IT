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
}
