import java.util.List;
import org.sql2o.*;

public class Owner {
  private int id;
  private String owner_name;
  private String email;
  private String profile_pic;

  public int getId() {
    return id;
  }

  public String getOwnerName() {
    return owner_name;
  }

  public String getEmail() {
    return email;
  }

  public String getProfilePic() {
    return profile_pic;
  }

  public Owner(String owner_name, String email, String profile_pic) {
    this.owner_name = owner_name;
    this.email = email;
    this.profile_pic = profile_pic;
  }

  @Override
  public boolean equals(Object otherOwner) {
    if(!(otherOwner instanceof Owner)) {
      return false;
    } else {
      Owner newOwner = (Owner) otherOwner;
      return this.getOwnerName().equals(newOwner.getOwnerName()) &&
             this.getEmail().equals(newOwner.getEmail()) &&
             this.getProfilePic().equals(newOwner.getProfilePic()) &&
             this.getId() == newOwner.getId();
    }
  }

  public static List<Owner> all() {
    String sql = "SELECT * FROM owners ORDER BY owner_name ASC";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Owner.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO owners (owner_name, email, profile_pic) VALUES (:owner_name, :email, :profile_pic)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("owner_name", this.owner_name)
          .addParameter("email", this.email)
          .addParameter("profile_pic", this.profile_pic)
          .executeUpdate()
          .getKey();
    }
  }

  public static Owner find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM owners WHERE id=:id";
      Owner owner = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Owner.class);
      return owner;
    }
  }

  public void update(String owner_name, String email, String profile_pic) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE owners SET owner_name=:owner_name, email=:email, profile_pic=:profile_pic WHERE id=:id";
      con.createQuery(sql)
        .addParameter("owner_name", owner_name)
        .addParameter("email", email)
        .addParameter("profile_pic", profile_pic)
        .addParameter("id", id)
        .executeUpdate();
    }
  }


}
