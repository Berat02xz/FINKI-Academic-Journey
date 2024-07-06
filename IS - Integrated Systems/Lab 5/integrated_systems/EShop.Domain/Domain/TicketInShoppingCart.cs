namespace MusicStore.Domain.Domain
{
    public class TicketInShoppingCart : BaseEntity
    {
        public Guid TicketId { get; set; }
        public Ticket? Ticket { get; set; }
        public Guid ShoppingCartId { get; set; }
        public ShoppingCart? ShoppingCart { get; set; }
        public int Quantity { get; set; }
    }
}
