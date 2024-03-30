namespace MovieApp.Models
{
    public class TicketInOrder
    {
        public Guid Id { get; set; }

        public Guid TicketId { get; set; }
        public Ticket? Ticket { get; set; }

        public string? UserId { get; set; }
        public EShopApplicationUser? User { get; set; }

        public int Quantity { get; set; }
    }
}
