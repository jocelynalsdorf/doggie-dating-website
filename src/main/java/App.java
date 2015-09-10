
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
      
      get("/new-account", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        //new Owner
        String name = request.queryParams("ownername");
        String contact = request.queryParams("ownercontact");
        String ownPic = request.queryParams("owner_pic");
        Owner newOwner = new Owner(name, contact, ownPic);

        //new Dog
        String dogName = request.queryParams("doggyname");
        String dogSum = request.queryParams("summary");
        String dogPic = request.queryParams("dog_pic");
        Dog newDog = new Dog(dogName, dogSum, dogPic, newOwner.getId());

        //get interests
        Integer interestOne = request.queryParams(Integer.parseInt("group1"));
        Integer interestTwo = request.queryParams(Integer.parseInt("group2"));
        Integer interestThree = request.queryParams(Integer.parseInt("group3"));
        Integer newInterests[] = new Integer[interestOne, interestTwo, interestThree];
        newDog.setInterest(newInterests[]);

        response.redirect("/profile/" +newDog.getId());
        return null;
      });

      get("/profile", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
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
    }



}//end of App
