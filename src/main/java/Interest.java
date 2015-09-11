import java.util.List;
import org.sql2o.*;


  public class Interest {
    private int id;
    private String name;

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public Interest(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object otherInterest) {
      if(!(otherInterest instanceof Interest)) {
        return false;
      } else {
        Interest newInterest = (Interest) otherInterest;
        return this.getId() == newInterest.getId() &&
               this.getName().equals(newInterest.getName());
      }
    }

    public static List<Interest> all() {
      String sql = "SELECT * FROM interests";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Interest.class);
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO interests (name) VALUES (:name)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .executeUpdate()
          .getKey();
      }
    }

    public static Interest find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM interests WHERE id=:id";
        Interest interest = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Interest.class);
        return interest;
      }
    }

    public void update(String name) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE interests SET name=:name WHERE id=:id";
        con.createQuery(sql)
          .addParameter("name", name)
          .addParameter("id", id)
          .executeUpdate();
      }
    }



}//ends class
