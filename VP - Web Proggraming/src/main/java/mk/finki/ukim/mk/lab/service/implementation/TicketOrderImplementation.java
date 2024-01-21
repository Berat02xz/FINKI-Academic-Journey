package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.jpa.TicketOrderRepositoryJPA;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderImplementation implements TicketOrderService {

    public TicketOrderRepositoryJPA ticket;

    public TicketOrderImplementation(TicketOrderRepositoryJPA ticket){
        this.ticket = ticket;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets, Long id){
        ticket.save(new TicketOrder(movieTitle,clientName,address,numberOfTickets,id));
        return null;
    }


    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        ticket.save(new TicketOrder(movieTitle,clientName,address,numberOfTickets));
        return null;
    }
}
