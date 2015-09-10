import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;
import java.util.*;

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

    @Test
    public void getOwner_returnCorrectOwner() {
      Owner myOwner = new Owner("Max", "max@gmail.com", "url");
      myOwner.save();
      Dog myDog = new Dog("Bailey", "url", "Bailey likes ice cream", myOwner.getId());
      myDog.save();
      assertTrue(myOwner.equals(myDog.getOwner().get(0)));
    }

    @Test
    public void delete_deleteDogFromDatabase() {
      Owner myOwner = new Owner("Max", "max@gmail.com", "url");
      myOwner.save();
      Dog myDog = new Dog("Bailey", "url", "Bailey likes ice cream", myOwner.getId());
      myDog.save();
      myDog.delete();
      assertEquals(0, Dog.all().size());
    }

    @Test
    public void getInterest_returnsBooleanArray_true() {
      Dog myDog = new Dog("Bella", "url", "Bella likes fruit", 1);
      myDog.save();
      Interest myInterests = new Interest(myDog.getId(), true, true, true, true, true);
      myInterests.save();
      Interest newInterests = myDog.getInterests();
      assertEquals(myInterests, newInterests);
    }

    @Test
    public void getScore_returnCorrectScore(){
      Dog firstDog = new Dog("Bella", "url", "Bella likes fruit", 1);
      firstDog.save();
      Dog secondDog = new Dog("Bob", "url", "Bob likes ice cream", 2);
      secondDog.save();
      Interest firstInterests = new Interest(firstDog.getId(), false, true, true, true, true);
      firstInterests.save();
      Interest secondInterests = new Interest(secondDog.getId(), false, true, true, true, true);
      secondInterests.save();
      firstDog.setMatches(secondDog.getId());
      assertEquals(5, firstDog.getScore(secondDog.getId()));
    }

    @Test
    public void setILike_savesTrueIntoDatabase() {
      Dog firstDog = new Dog("Bumble", "eee", "eeeeee", 1);
      firstDog.save();
      Dog secondDog = new Dog("Mumble", "ooo", "oooo", 1);
      secondDog.save();
      firstDog.setMatches(secondDog.getId());
      firstDog.setILike(secondDog.getId());
      assertEquals(1, firstDog.getMatches().size());
    }


    @Test
    public void getMatches_returnsAllDogMatches() {
      Dog jocelyn = new Dog("Jesse", "url", "Cute cockerspaniel", 1);
      jocelyn.save();
      Dog yvonne = new Dog("elephant", "url", "actually a hamster", 1);
      yvonne.save();
      Dog morgan = new Dog("Nina", "url", "old but still adorable", 1);
      morgan.save();
      Dog teresa = new Dog("goon crowley", "url", "actually a cat", 1);
      teresa.save();
      yvonne.setMatches(jocelyn.getId());
      yvonne.setMatches(morgan.getId());
      yvonne.setMatches(teresa.getId());
      yvonne.setILike(jocelyn.getId());
      yvonne.setIDislike(morgan.getId());
      yvonne.setILike(teresa.getId());

      assertEquals(2, yvonne.getMatches().size());
    }
  }//end of class
