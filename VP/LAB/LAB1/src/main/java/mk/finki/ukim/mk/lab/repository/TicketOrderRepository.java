package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository //annotation that lets the server know that this is a repo
public class TicketOrderRepository {
    public static List<TicketOrder> orders = new ArrayList<>();

    public void addOrder(String movieTitleTitle, String clientName, String clientAddress, int numberOfTickets){
        orders.add(new TicketOrder(movieTitleTitle,clientName,clientAddress,numberOfTickets));
    }

    public TicketOrder getOrder(){
        return orders.get(1);
    }

    public List<TicketOrder> getOrders (){
        return orders;
    }

}
