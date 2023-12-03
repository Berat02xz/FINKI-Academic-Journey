package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShoppingCart {

    private Long id;
    private User user;
    private LocalDateTime dateCreated;
    private List<TicketOrder> ticketOrders;

    public ShoppingCart(User user, LocalDateTime dateCreated, List<TicketOrder> ticketOrders) {
        this.id = (long) (Math.random() * 1000);
        this.user = user;
        this.dateCreated = dateCreated;
        this.ticketOrders = ticketOrders;
    }

    public ShoppingCart() {

    }
}
