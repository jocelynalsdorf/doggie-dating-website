import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;

public class InterestTest {

//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void all_emptyAtFirst() {
//     assertEquals(Interest.all().size(), 0);
//   }
//
//   @Test public void equals_returnsTrueIfInterestsAreTheSame() {
//     Interest firstInterest = new Interest(1, true, true, true, true, true);
//     Interest secondInterest = new Interest(1, true, true, true, true, true);
//     assertTrue(firstInterest.equals(secondInterest));
//   }
//
//   @Test
//   public void save_savesIntoDatabase_true() {
//     Interest newInterest = new Interest(1, true, true, true, true, true);
//     newInterest.save();
//     assertTrue(Interest.all().get(0).equals(newInterest));
//   }
//
//   @Test
//   public void find_findsInterestsInDatabase_true() {
//     Interest newInterest = new Interest(1, true, true, true, true, true);
//     newInterest.save();
//     Interest savedInterest = Interest.find(newInterest.getDogId());
//     assertTrue(newInterest.equals(savedInterest));
//   }
//
//   @Test
//   public void update_updatesInterestsInDatabase_true() {
//     Interest newInterest = new Interest(1, true, true, true, true, true);
//     newInterest.save();
//     boolean swimming = false;
//     boolean eating = false;
//     boolean frisbee = false;
//     boolean running = false;
//     boolean barking = false;
//     newInterest.update(swimming,eating,frisbee,running,barking);
//     assertTrue(Interest.all().get(0).getSwimming().equals(swimming));
//     assertTrue(Interest.all().get(0).getEating().equals(eating));
//     assertTrue(Interest.all().get(0).getFrisbee().equals(frisbee));
//     assertTrue(Interest.all().get(0).getRunning().equals(running));
//     assertTrue(Interest.all().get(0).getBarking().equals(barking));
//   }
}
