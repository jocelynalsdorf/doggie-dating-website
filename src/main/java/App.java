
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

      get("/", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/home.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/login", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
        String password = request.queryParams("password");
        Dog myDog = Dog.getDog(name, password);
        request.session().attribute("dogID", myDog.getId());

        response.redirect("/profile/" +myDog.getId());
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
        Dog newDog = new Dog(dogName, dogSum, dogPic, newOwner.getId(), dogPW);
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
        Dog myDog = Dog.find(dog_id);
        model.put("dogId", request.session().attribute("dogId"));
        model.put("owner", myDog.getOwner());
        model.put("dog", myDog);
        model.put("template", "templates/profile.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/all-dogs", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/all-dogs.vtl");
        model.put("dogId", request.session().attribute("dogId"));
        model.put("dogs", Dog.all());
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/update", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("dogId", request.session().attribute("dogId"));
        int dog_id = request.session().attribute("dogId");
        Dog myDog = Dog.find(dog_id);
        model.put("owner", myDog.getOwner());
        model.put("dog", myDog);
        model.put("template", "templates/edit-profile.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/update", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int dog_id = request.session().attribute("dogId");
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
        myDog.addInterest(interestOne);
        myDog.addInterest(interestTwo);
        myDog.addInterest(interestThree);

        model.put("owner", myDog.getOwner());
        model.put("dog", myDog);
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
        response.redirect("/");
        return null;
      });

  }// end of main
}//end of App
