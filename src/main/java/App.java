
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

      get("/new-account", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/new-account.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      // post("/new-account", (request, response) -> {
      //   HashMap<String, Object> model = new HashMap<String, Object>();
      //   //new Owner
      //   String name = request.queryParams("ownername");
      //   String contact = request.queryParams("ownercontact");
      //   String ownPic = request.queryParams("owner_pic");
      //   Owner newOwner = new Owner(name, contact, ownPic);
      //
      //   //new Dog
      //   String dogName = request.queryParams("doggyname");
      //   String dogSum = request.queryParams("summary");
      //   String dogPic = request.queryParams("dog_pic");
      //   Dog newDog = new Dog(dogName, dogSum, dogPic, newOwner.getId());
      //
      //   //get interests
      //   String interestOne = request.queryParams("group1");
      //   String interestTwo = request.queryParams("group2");
      //   String interestThree = request.queryParams("group3");
      //   newDog.addInterest(interestOne);
      //   newDog.addInterest(interestTwo);
      //   newDog.addInterest(interestThree);
      //
      //   request.session().attribute("dogId", newDog.getId());
      //
      //   response.redirect("/profile/" +newDog.getId());
      //   return null;
      // });

      get("/profile/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int dog_id = Integer.parseInt(request.queryParams("id"));
        Dog myDog = Dog.find(dog_id);
        model.put("owner", myDog.getOwner());
        model.put("dog", myDog);
        model.put("template", "templates/profile.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/all-dogs", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/all-dogs.vtl");
        model.put("dogs", Dog.all());
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/edit", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/edit-profile.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());


      get("/featured", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/featured.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());




      // may need to use post
    //   get("/dogs/:id/delete", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     int dog_id = Integer.parseInt(request.queryParams("id"));
    //     Dog myDog = Dog.find(dog_id);
    //     myDog.delete();
    //     response.redirect("/");
    //   return null;
    // });

  }// end of main
}//end of App
