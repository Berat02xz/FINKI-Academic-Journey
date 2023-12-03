package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //(automatic getter/setter annotation
@AllArgsConstructor //automatic constructor
public class TicketOrder {
    public String clientName;
    public String clientAddress;
    public String movieTitle;
    public int numberOfTickets;


}