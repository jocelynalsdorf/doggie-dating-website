import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

  public class Dog {
    private int id, owner_id;
  private String name, profile_pic, summary;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getProfilePic(){
    return profile_pic;
  }

  public String getSummary(){
    return summary;
  }

  public int getOwnerId() {
    return owner_id;
  }

  public Dog(String name, String profile_pic, String summary, int owner_id) {
    this.name = name;
    this.profile_pic = profile_pic;
    this.summary = summary;
    this.owner_id = owner_id;
  }

  @Override
  public boolean equals(Object otherDog){
    if (!(otherDog instanceof Dog)) {
      return false;
    } else {
      Dog newDog = (Dog) otherDog;
      return this.getId() == newDog.getId() &&
              this.getName().equals(newDog.getName()) &&
              this.getProfilePic().equals(newDog.getProfilePic()) &&
              this.getSummary().equals(newDog.getSummary()) &&
              this.getOwnerId() == newDog.getOwnerId();
    }
  }

  public static List<Dog> all() {
    String sql = "SELECT * FROM dogs ORDER BY name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Dog.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO dogs (name, profile_pic, summary, owner_id) VALUES (:name, :profile_pic, :summary, :owner_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("profile_pic", this.profile_pic)
        .addParameter("summary", this.summary)
        .addParameter("owner_id", this.owner_id)
        .executeUpdate()
        .getKey();
    }
  }


  public static Dog find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM dogs where id=:id";
      Dog dog = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Dog.class);
      return dog;
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE dogs SET name =:name, profile_pic =:profile_pic, summary =:summary, owner_id = :owner_id WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("profile_pic", profile_pic)
        .addParameter("summary", summary)
        .addParameter("owner_id", owner_id)
        .executeUpdate();
    }
  }

  public List<Owner> getOwner() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM owners WHERE id =:owner_id";
      List<Owner> owner = con.createQuery(sql)
        .addParameter("owner_id", this.owner_id)
        .executeAndFetch(Owner.class);
      return owner;
    }
  }

  public static List<Dog> searchByDog(String dog_name) {
    String sql = "SELECT * FROM dogs WHERE name LIKE '%" + dog_name + "%'";
    List<Dog> searchResults;
    try (Connection con = DB.sql2o.open()) {
      searchResults = con.createQuery(sql)
        .executeAndFetch(Dog.class);
    }
    return searchResults;
  }


  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM dogs where id=:id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }


   public Interest getInterests(){
     String sql =  "SELECT * FROM interests WHERE dog_id = :id";
     try (Connection con = DB.sql2o.open()){
       return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetchFirst(Interest.class);
     }
   }

   public void setMatches(int dog_friend_id){
     String sql = "INSERT INTO match (dog_id, dog_friend_id) VALUES (:id, :dog_friend_id)";
     try (Connection con = DB.sql2o.open()){
       con.createQuery(sql)
        .addParameter("id", this.getId())
        .addParameter("dog_friend_id", dog_friend_id)
        .executeUpdate();
     }
   }

   public int getScore(int dog_friend_id){
     Dog dogFriend = Dog.find(dog_friend_id);
     int score = 0;
     Interest thisInterest = this.getInterests();
     Interest friendInterest = dogFriend.getInterests();
     Boolean[] thisInterestArray  = thisInterest.toBooleanArray();
     Boolean[] friendInterestArray  = friendInterest.toBooleanArray();

     for (int i =0; i< thisInterestArray.length; i++){
       if(thisInterestArray[i].equals(friendInterestArray[i])){
         score += 1;
       }
     }

     String sql = "UPDATE match SET interest_score = :score WHERE dog_id = :id AND dog_friend_id = :dog_friend_id";
     try (Connection con = DB.sql2o.open()){
       con.createQuery(sql)
       .addParameter("id", this.getId())
       .addParameter("dog_friend_id", dog_friend_id)
       .addParameter("score", score)
       .executeUpdate();
     }
     return score;
   }

   public void setILike(int dog_friend_id) {
     String sql = "UPDATE match SET i_like=true WHERE dog_friend_id=:dog_friend_id AND dog_id=:id";
     try(Connection con = DB.sql2o.open()) {
       con.createQuery(sql)
        .addParameter("dog_friend_id", dog_friend_id)
        .addParameter("id", id)
        .executeUpdate();
     }
   }

   public void setIDislike(int dog_friend_id) {
     try(Connection con = DB.sql2o.open()) {
       String sql = "UPDATE match SET i_like=false WHERE dog_friend_id=:dog_friend_id AND dog_id=:id";
       con.createQuery(sql)
        .addParameter("dog_friend_id", dog_friend_id)
        .addParameter("id", id)
        .executeUpdate();
     }
   }


   public List<Dog> getMatches(){
     String sql = "SELECT dogs.* FROM match JOIN dogs ON (dogs.id = match.dog_friend_id) WHERE match.dog_id =:id AND match.i_like = true";
     try(Connection con = DB.sql2o.open()){
       List<Dog> dogs = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Dog.class);
      return dogs;
     }
   }

  }//end of class
