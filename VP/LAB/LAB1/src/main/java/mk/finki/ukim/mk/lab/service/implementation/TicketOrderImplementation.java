package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.impl.TicketOrderRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderImplementation implements TicketOrderService {

    public TicketOrderRepository ticket = new TicketOrderRepository();

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets){
        ticket.addOrder(movieTitle,clientName,address,numberOfTickets);
        return ticket.getOrder();
    }

}
