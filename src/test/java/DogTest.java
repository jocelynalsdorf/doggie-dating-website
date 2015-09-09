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

    @Test
    public void save_saveIntoDatabase_true() {
      Dog myDog = new Dog("Bella", "url", "Bella likes fruit", 1);
      myDog.save();
      assertTrue(Dog.all().get(0).equals(myDog));
    }

    @Test
    public void find_findDogInDatabase_true() {
      Dog myDog = new Dog("Bailey", "url", "Bailey likes ice cream", 1);
      myDog.save();
      Dog savedDog = Dog.find(myDog.getId());
      assertTrue(myDog.equals(savedDog));
    }

}
