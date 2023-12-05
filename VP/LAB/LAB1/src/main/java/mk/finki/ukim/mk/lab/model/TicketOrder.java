package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data //(automatic getter/setter annotation
@AllArgsConstructor
@Entity
@Table(name = "TicketOrder")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    public User user;

    public String movieTitle;

    public String clientName;

    public String address;

    public int numberOfTickets;


    public TicketOrder() {

    }

    public TicketOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        this.clientName = clientName;
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
    }

    public TicketOrder(String movieTitle, String clientName, String address, int numberOfTickets, Long id) {
        this.clientName = clientName;
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
        this.id = Long.valueOf(1);
    }
}