package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //(automatic getter/setter annotation
@AllArgsConstructor //automatic constructor
public class TicketOrder {
    public String movieTitle;
    public String clientName;
    public String clientAddress;
    public int numberOfTickets;
}