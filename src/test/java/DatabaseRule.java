import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doggie_dating_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteDogsQuery = "DELETE FROM dogs *;";
      String deleteOwnersQuery = "DELETE FROM owners *;";
      String deleteInterestsQuery = "DELETE FROM interests *;";
      String deleteMatchQuery = "DELETE FROM match *;";
      con.createQuery(deleteDogsQuery).executeUpdate();
      con.createQuery(deleteOwnersQuery).executeUpdate();
      con.createQuery(deleteInterestsQuery).executeUpdate();
      con.createQuery(deleteMatchQuery).executeUpdate();
    }
  }
}
