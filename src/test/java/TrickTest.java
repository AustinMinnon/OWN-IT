import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.apache.commons.lang.WordUtils;

public class TrickTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // @Test
  // public void all_emptyAtFirst() {
  //   assertEquals(Trick.all().size(), 0);
  // }
}
