import java.util.List;
import org.sql2o.*;


  public class Interest {
    private int id;
    private int dog_id;
    private Boolean swimming;
    private Boolean eating;
    private Boolean frisbee;
    private Boolean running;
    private Boolean barking;

    public int getId() {
      return id;
    }

    public int getDogId() {
      return dog_id;
    }

    public Boolean getSwimming() {
      return swimming;
    }

    public Boolean getEating() {
      return eating;
    }

    public Boolean getFrisbee() {
      return frisbee;
    }

    public Boolean getRunning() {
      return running;
    }

    public Boolean getBarking() {
      return barking;
    }

    public Interest(int dog_id, Boolean swimming, Boolean eating, Boolean frisbee, Boolean running, Boolean barking) {
      this.dog_id = dog_id;
      this.swimming = swimming;
      this.eating = eating;
      this.frisbee = frisbee;
      this.running = running;
      this.barking = barking;
    }

    @Override
    public boolean equals(Object otherInterest) {
      if(!(otherInterest instanceof Interest)) {
        return false;
      } else {
        Interest newInterest = (Interest) otherInterest;
        return this.getDogId() == newInterest.getDogId() &&
               this.getSwimming().equals(newInterest.getSwimming()) &&
               this.getEating().equals(newInterest.getEating()) &&
               this.getFrisbee().equals(newInterest.getFrisbee()) &&
               this.getRunning().equals(newInterest.getRunning()) &&
               this.getBarking().equals(newInterest.getBarking()) &&
               this.getId() == newInterest.getId();
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
        String sql = "INSERT INTO interests (dog_id, swimming, eating, frisbee, running, barking) VALUES (:dog_id, :swimming, :eating, :frisbee, :running, :barking)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("dog_id", this.dog_id)
          .addParameter("swimming", this.swimming)
          .addParameter("eating", this.eating)
          .addParameter("frisbee", this.frisbee)
          .addParameter("running", this.running)
          .addParameter("barking", this.barking)
          .executeUpdate()
          .getKey();
      }
    }

    public static Interest find(int dog_id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM interests WHERE dog_id=:dog_id";
        Interest interest = con.createQuery(sql)
          .addParameter("dog_id", dog_id)
          .executeAndFetchFirst(Interest.class);
        return interest;
      }
    }

    public void update(boolean swimming, boolean eating, boolean frisbee, boolean running, boolean barking) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE interests SET swimming=:swimming, eating=:eating, frisbee=:frisbee, running=:running, barking=:barking WHERE dog_id=:dog_id";
        con.createQuery(sql)
          .addParameter("swimming", swimming)
          .addParameter("eating", eating)
          .addParameter("frisbee", frisbee)
          .addParameter("running", running)
          .addParameter("barking", barking)
          .addParameter("dog_id", dog_id)
          .executeUpdate();
      }
    }

    public Boolean[] toBooleanArray() {
      Boolean[] booleanArray = {swimming, eating, frisbee, running, barking};
      return booleanArray;
    }

}//ends class
