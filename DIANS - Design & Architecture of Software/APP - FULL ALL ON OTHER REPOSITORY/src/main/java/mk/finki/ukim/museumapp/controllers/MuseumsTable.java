package mk.finki.ukim.museumapp.controllers;


import mk.finki.ukim.museumapp.PipeAndFilter.Service.MuseumService;
import mk.finki.ukim.museumapp.PipeAndFilter.Service.ReviewService;
import mk.finki.ukim.museumapp.PipeAndFilter.Service.UserService;
import mk.finki.ukim.museumapp.PipeAndFilter.model.Museum;
import mk.finki.ukim.museumapp.PipeAndFilter.model.Review;
import mk.finki.ukim.museumapp.PipeAndFilter.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/")
public class MuseumsTable {
    private final MuseumService museumService;
    private final UserService userService;
    public final ReviewService reviewService;

    public MuseumsTable(MuseumService museumService, UserService userService, ReviewService reviewService) {
        this.museumService = museumService;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GetMapping("/edit.html")
        public String getMuseums(Model model, @RequestParam(required = false) String error) {
            List<Museum> museums = museumService.getMuseums();
            model.addAttribute("museums", museums);
            model.addAttribute("bodyContent", "museums");
            model.addAttribute("error", error);
            return "edit";
        }


        @GetMapping
        public String getIndex(Model model) {
            List<Museum> museums = museumService.getMuseums();
            model.addAttribute("museums", museums);

            return "index";
        }

        @GetMapping("/search")
        String getSearch(Model model, @RequestParam String search) {
            //if search is empty return all museums
            if(search.isEmpty()) {
                List<Museum> museums = museumService.getMuseums();
                model.addAttribute("museums", museums);
                return "index";
            }
            List<Museum> filteredMuseums = museumService.searchmuseums(search);
            model.addAttribute("museums", filteredMuseums);
            return "index";
        }

        @GetMapping("/OpenNow")
        String getOpenNow(Model model) {
               List<Museum> filteredMuseums = museumService.getOpenNow();
               model.addAttribute("museums", filteredMuseums);
               return "index";
        }

        @GetMapping("/freeentry")
        String getFreeEntry(Model model) {
               List<Museum> filteredMuseums = museumService.getFreeEntry();
               model.addAttribute("museums", filteredMuseums);
               return "index";
        }

        @GetMapping("/InternetAccess")
        String getInternetAccess(Model model) {
               List<Museum> filteredMuseums = museumService.getInternetAccess();
               model.addAttribute("museums", filteredMuseums);
               return "index";
        }

        @GetMapping("/All")
        String getAll(Model model) {
               List<Museum> museums = museumService.getMuseums();
               model.addAttribute("museums", museums);
               return "index";
        }

        @GetMapping("/Skopje")
        String getSkopje(Model model) {
               List<Museum> filteredMuseums = museumService.getSkopje();
               model.addAttribute("museums", filteredMuseums);
               return "index";
        }

        @PostMapping("/LoginMethod")
        String getLoginMethod(Model model, @RequestParam String username, @RequestParam String password){

        User user = userService.getUser(username, password);


        //check if role admin else return index
        if (user != null && user.getRole().equals("Admin")) {
                model.addAttribute("user", user);
                return "redirect:/edit.html";
            }
        if(user != null && user.getRole().equals("User")) {
            model.addAttribute("user", user);
            return "redirect:/";
        }

        if(user == null ) {
            return "redirect:/Login.html?error=Invalid username or password";
        }
            return "redirect:/Login.html?error=Error, Try again";
        }

        @GetMapping("/Login.html")
        String getLogin(Model model) {
        System.out.println("Login");
            return "Login";
        }

        @GetMapping("/Register.html")
        String getRegister(Model model) {
        System.out.println("Register");
            return "Register";
        }

        @PostMapping("/RegisterMethod")
        String getRegisterMethod(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String email){

            Boolean userExists = userService.userExist(username);
            if(userExists) {
                return "redirect:/Register.html?error=Username already exists";
            }

            User user = userService.createUser(username, password, email);
            model.addAttribute("user", user);
            return "redirect:/";

        }

        @GetMapping("/museums/create")
        public String createMuseum(Model model) {
        System.out.println("EDIT MUSEUM");
            return "MuseumEdit";
        }

        @GetMapping("/museums/editreviews")
        public String editReviews(Model model) {
            List<Review> reviews = reviewService.GetAllReviews();
            model.addAttribute("reviews", reviews);
            return "EditReviews";
        }

        @PostMapping("/museum/add")
        public String createMuseum(Model model, @RequestParam int id, @RequestParam String name, @RequestParam String latitude, @RequestParam String longitude, @RequestParam String street, @RequestParam String email, @RequestParam String internetAccess, @RequestParam String wikidata, @RequestParam String openingHours, @RequestParam String phone, @RequestParam String fee, @RequestParam String charge, @RequestParam String website) {

            if(id != 0) {
                museumService.deleteMuseum(id);
                System.out.println("MUSEUM DELETED BEFORE ADDING NEW");
            }

            //cast string latitude to double
            double lat = Double.parseDouble(latitude);
            //cast string longitude to double
            double lon = Double.parseDouble(longitude);

            Museum museum = museumService.createMuseum(name, lat, lon, street, email, internetAccess, wikidata, openingHours, phone, fee, charge, website);
            System.out.println("MUSEUM CREATED");
            return "redirect:/edit.html";
        }

        @GetMapping("/museums/delete/{id}")
        public String deleteMuseum(Model model, @PathVariable int id) {
            museumService.deleteMuseum(id);
            System.out.println("MUSEUM DELETED");
            return "redirect:/edit.html";
        }

        @GetMapping("/reviews/delete/{id}")
        public String deleteReview(Model model, @PathVariable int id) {
            reviewService.deleteReview(id);
            System.out.println("REVIEW DELETED");
            return "redirect:/museums/editreviews";
        }

        @GetMapping("/museums/edit/{id}")
        public String editMuseum(Model model, @PathVariable int id) {
            Museum museum = museumService.getMuseum(id);
            model.addAttribute("museum", museum);
            return "MuseumEdit";
        }

        @GetMapping("/addreview/{id}")
        public String addReview(Model model, @PathVariable int id) {
            Museum museum = museumService.getMuseum(id);
            model.addAttribute("museum", museum);
            return "AddReview";
        }

        @PostMapping("/museum/createReview")
        public String addReview(Model model, @RequestParam int museum_id, @RequestParam String review, @RequestParam String username, @RequestParam int stars) {
            Review review1 = reviewService.saveReview(review, username, stars, museum_id);

            System.out.println("REVIEW CREATED");
            return "redirect:/";
        }

}
