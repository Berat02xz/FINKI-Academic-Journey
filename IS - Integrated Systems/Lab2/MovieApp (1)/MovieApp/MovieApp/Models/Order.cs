namespace MovieApp.Models
{
    public class Order
    {
        public Guid Id { get; set; }
        
        public string? UserId { get; set; }
        public EShopApplicationUser? User { get; set; }

        public int TotalPrice { get; set; }

        public ICollection<TicketInOrder> Tickets { get; set; }
    }
}
