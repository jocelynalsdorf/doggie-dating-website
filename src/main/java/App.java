
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // before("/profile/:id", (request, response) -> {
    //   boolean session = request.session().isNew();
    //   if(session){
    //     halt(401, "Please log in!!");
    //   }
    // });
    //
    // before("/update/:id", (request, response) -> {
    //   String dogId = request.params("id");
    //   if(dogId=="$dogId"){
    //     halt(401, "Please log in!!");
    //   }
    // });

      get("/update/$dogId", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
        halt(403, "Please log in or create an account!");
        return null;
      });

      get("/profile/$dogId", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
        halt(403, "Please log in or create an account!");
        return null;
      });

      get("/", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();

        model.put("dogId", request.session().attribute("dogId"));
        model.put("template", "templates/home.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/login", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/login.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/login", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
        String password = request.queryParams("password");
        Dog myDog = Dog.getDog(name, password);
        //int dogId = myDog.getId();
        request.session().attribute("dogId", myDog.getId());
        response.redirect("/profile/" +myDog.getId());
        return null;
      });

      get("/logout", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        request.session().removeAttribute("dogId");
        response.redirect("/");
        return null;
      });

      get("/new-account", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/new-account.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/new-account", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        //new Owner
        String name = request.queryParams("ownername");
        String contact = request.queryParams("ownercontact");
        String ownPic = request.queryParams("owner_pic");
        Owner newOwner = new Owner(name, contact, ownPic);
        newOwner.save();

        //new Dog
        String dogName = request.queryParams("doggyname");
        String dogSum = request.queryParams("summary");
        String dogPic = request.queryParams("dog_pic");
        String dogPW = request.queryParams("password");
        Dog newDog = new Dog(dogName, dogPic, dogSum, newOwner.getId(), dogPW);
        newDog.save();
        //get interests
        int interestOne = Integer.parseInt(request.queryParams("group1"));
        int interestTwo = Integer.parseInt(request.queryParams("group2"));
        int interestThree = Integer.parseInt(request.queryParams("group3"));
        newDog.addInterest(interestOne);
        newDog.addInterest(interestTwo);
        newDog.addInterest(interestThree);

        request.session().attribute("dogId", newDog.getId());
        response.redirect("/profile/" +newDog.getId());
        return null;
      });


      get("/profile/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int dog_id = Integer.parseInt(request.params("id"));
        // if(dog_id == null) {
        //   halt(response.redirect("/", 301) = 301);
        // };
        Dog myDog = Dog.find(dog_id);
        //int score = myDog.getScore(request.session().attribute("dogId"));

        model.put("score", myDog.getScore(request.session().attribute("dogId")));
        model.put("dogId", request.session().attribute("dogId"));
        model.put("owner", myDog.getOwner());
        model.put("dog", myDog);
        model.put("template", "templates/profile.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/profile/:id/connect/:friend_id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int dog_id = Integer.parseInt(request.params("id"));
        int friend_id = Integer.parseInt(request.params("friend_id"));
        Dog myDog = Dog.find(dog_id);
        myDog.setMatches(friend_id);
        myDog.setILike(friend_id);
        int dogId = request.session().attribute("dogId");
        response.redirect("/profile/" + dogId);
        return null;
      });

      get("/all-dogs", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/all-dogs.vtl");
        model.put("dogId", request.session().attribute("dogId"));
        model.put("dogs", Dog.all());
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/update/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int dog_id = Integer.parseInt(request.params("id"));
        Dog myDog = Dog.find(dog_id);
        model.put("owner", myDog.getOwner());
        model.put("dogId", request.session().attribute("dogId"));
        model.put("dog", myDog);
        model.put("template", "templates/edit-profile.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/update/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int dog_id = Integer.parseInt(request.params("id"));
        Dog myDog = Dog.find(dog_id);
        Owner myOwner = myDog.getOwner();

        String name = request.queryParams("ownername");
        String contact = request.queryParams("ownercontact");
        String ownPic = request.queryParams("owner_pic");
        myOwner.update(name, contact, ownPic);

        //new Dog
        String dogName = request.queryParams("doggyname");
        String dogSum = request.queryParams("summary");
        String dogPic = request.queryParams("dog_pic");
        myDog.update(name, dogPic, dogSum, myOwner.getId());

        //get interests
        int interestOne = Integer.parseInt(request.queryParams("group1"));
        int interestTwo = Integer.parseInt(request.queryParams("group2"));
        int interestThree = Integer.parseInt(request.queryParams("group3"));
        myDog.deleteInterests();
        myDog.addInterest(interestOne);
        myDog.addInterest(interestTwo);
        myDog.addInterest(interestThree);

        model.put("owner", myDog.getOwner());
        model.put("dog", myDog);
        model.put("dogId", request.session().attribute("dogId"));
        response.redirect("/profile/" + dog_id);
        return null;
      });

      get("/featured", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/featured.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/dogs/:id/delete", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int dog_id = Integer.parseInt(request.params("id"));
        Dog myDog = Dog.find(dog_id);
        myDog.delete();
        request.session().removeAttribute("dogId");
        response.redirect("/");
        return null;
      });

  }// end of main
}//end of App
