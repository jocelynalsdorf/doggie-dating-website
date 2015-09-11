import java.util.List;
import java.util.ArrayList;
import java.util.*;
import org.sql2o.*;
import org.apache.commons.lang.ArrayUtils;

public class Dog {
  private int id, owner_id;
  private String name, profile_pic, summary;
  private String password;

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

  public Dog(String name, String profile_pic, String summary, int owner_id, String password) {
    this.name = name;
    this.profile_pic = profile_pic;
    this.summary = summary;
    this.owner_id = owner_id;
    this.password = password;
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

  public static Dog getDog(String name, String password) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM dogs WHERE name=:name AND password=:password";
      Dog dog = con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("password", password)
        .executeAndFetchFirst(Dog.class);
      return dog;
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

  public void update(String name, String profile_pic, String summary, int owner_id) {
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

  public Owner getOwner() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM owners WHERE id =:owner_id";
      Owner owner = con.createQuery(sql)
        .addParameter("owner_id", this.getOwnerId())
        .executeAndFetchFirst(Owner.class);
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
      String joinsql = "DELETE FROM dogs_interests where dog_id=:id";
      con.createQuery(joinsql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addInterest(int interest_id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO dogs_interests (dog_id, interest_id) VALUES (:dog_id, :interest_id)";
    con.createQuery(sql)
      .addParameter("dog_id", id)
      .addParameter("interest_id", interest_id)
      .executeUpdate();
    }
  }

  public List<Interest> getInterest() {
  try(Connection con = DB.sql2o.open()){
    String sql = "SELECT interests.* FROM dogs JOIN dogs_interests ON (dogs.id = dogs_interests.dog_id) JOIN interests ON (dogs_interests.interest_id = interests.id) WHERE dogs.id =:id";
    List<Interest> myInterests = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Interest.class);
    return myInterests;
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
     for (int i = 0; i< this.getInterest().size(); i++){
       for (int j= 0; j< dogFriend.getInterest().size(); j++){
         if (this.getInterest().get(i).equals(dogFriend.getInterest().get(j))){
           score += 1;
         }
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
