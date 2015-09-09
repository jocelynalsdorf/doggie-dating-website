import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;

public class DogTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
    public void all_emptyAtFirst() {
      assertEquals(Dog.all().size(), 0);
    }

    @Test
    public void equals_returnsTrueIfTheyAretheSame() {
      Dog firstDog = new Dog("Bailey", "url", "Bailey likes ice cream", 1);
      Dog secondDog = new Dog("Bailey", "url", "Bailey likes ice cream", 1);
      assertTrue(firstDog.equals(secondDog));
    }

  
}
