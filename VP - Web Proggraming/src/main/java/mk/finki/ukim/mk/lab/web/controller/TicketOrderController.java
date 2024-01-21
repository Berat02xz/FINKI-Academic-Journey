package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.jpa.TicketOrderRepositoryJPA;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketOrderController {

    public TicketOrderRepositoryJPA ticket;

    public void TicketOrderImplementation(TicketOrderRepositoryJPA ticket){
        this.ticket = ticket;
    }


    @GetMapping("/orderConfirmation")
    public String showOrderConfirmation(
            @RequestParam("selectedMovie") String selectedMovie,
            @RequestParam("numTickets") int numTickets,
            Model model) {
        // Process order and save in the database or repository if needed
        ticket.save(new TicketOrder(selectedMovie, "clientName", "address", numTickets, 1L));

        model.addAttribute("selectedMovie", selectedMovie);
        model.addAttribute("numTickets", numTickets);

        return "orderConfirmation";
    }
}
