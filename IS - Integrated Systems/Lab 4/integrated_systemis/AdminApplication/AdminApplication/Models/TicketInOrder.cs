using System.ComponentModel.DataAnnotations;


namespace AdminApplication.Models
{
    public class TicketInOrder 
    {

        public Guid TicketId { get; set; }
        public Ticket? OrderedProduct { get; set; }

        public Guid OrderId { get; set; }
        public Order? Order { get; set; }
        public int Quantity { get; set; }
    }
}
