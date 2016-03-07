import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.apache.commons.lang.WordUtils;


public class UserTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(User.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    User firstUser = new User("Tony Hawk");
    User secondUser = new User("Tony Hawk");
    assertTrue(firstUser.equals(secondUser));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    User myUser = new User("Tony Hawk");
    myUser.save();
    User savedUser = User.all().get(0);
    assertTrue(savedUser.equals(myUser));
  }

  @Test
  public void find_findsUserInDatabase_true() {
    User myUser = new User("Tony Hawk");
    myUser.save();
    User savedUser = User.find(myUser.getId());
    assertTrue(myUser.equals(savedUser));
  }

  @Test
  public void save_assignsIdToObject() {
    User myUser = new User("Tony Hawk");
    myUser.save();
    User savedUser = User.all().get(0);
    assertEquals(myUser.getId(), savedUser.getId());
  }
}
