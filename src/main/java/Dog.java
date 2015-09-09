import java.util.List;
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

  public Sting getProfile_pic(){
    return profile_pic;
  }

  public Sting getSummary(){
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
              this.getProfile_pic() == newDog.getProfile_pic() &&
              this.getSummary() == newDog.getSummary() &&
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
      String sql = "INSERT INTO dogs (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
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

  public List<Owner> getOwners() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM owners WHERE id =:owner_id";
      List<Owner> owners = con.createQuery(sql)
        .addParameter("owner_id", owner_id)
        .executeAndFetch(Owner.class);
        return owners;
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







  }//end of class
