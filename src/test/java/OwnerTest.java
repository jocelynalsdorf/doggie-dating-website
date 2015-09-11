import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class OwnerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void all_emptyAtFirst() {
      assertEquals(Owner.all().size(), 0);
    }

    @Test
    public void equals_returnsTrueIfOwnersAreSame() {
      Owner firstOwner = new Owner("Morgan", "email", "profileimg");
      Owner secondOwner = new Owner("Morgan", "email", "profileimg");
      assertTrue(firstOwner.equals(secondOwner));
    }

    @Test
    public void save_savesIntoDatabase_true() {
      Owner newOwner = new Owner("Morgan", "email", "profileimg");
      newOwner.save();
      assertTrue(Owner.all().get(0).equals(newOwner));
    }

    @Test
    public void find_findsOwnerInDatabase_true() {
      Owner newOwner = new Owner("Morgan", "email", "profileimg");
      newOwner.save();
      Owner savedOwner = Owner.find(newOwner.getId());
      assertTrue(newOwner.equals(savedOwner));
    }

    @Test
    public void update_updatesOwnerInDatabase_true() {
      Owner newOwner = new Owner("Morgan", "email", "profileimg");
      newOwner.save();
      String name = "Jocelyn";
      String email = "email2";
      String img = "pic";
      newOwner.update(name, email, img);
      assertTrue(Owner.all().get(0).getOwnerName().equals(name));
      assertTrue(Owner.all().get(0).getEmail().equals(email));
      assertTrue(Owner.all().get(0).getProfilePic().equals(img));
    }


  }//end of OwnerTest
