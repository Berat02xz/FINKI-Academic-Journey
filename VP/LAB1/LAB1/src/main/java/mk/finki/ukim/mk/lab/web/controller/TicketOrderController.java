package mk.finki.ukim.mk.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketOrderController {

    @GetMapping("/orderConfirmation")
    public String showOrderConfirmation(
            @RequestParam("selectedMovie") String selectedMovie,
            @RequestParam("numTickets") int numTickets,
            Model model) {
        // Process order and save in the database or repository if needed

        model.addAttribute("selectedMovie", selectedMovie);
        model.addAttribute("numTickets", numTickets);

        return "orderConfirmation";
    }
}
