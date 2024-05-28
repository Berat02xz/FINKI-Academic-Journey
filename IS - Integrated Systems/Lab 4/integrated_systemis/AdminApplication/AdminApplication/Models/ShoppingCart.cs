namespace AdminApplication.Models
{
    public class ShoppingCart 
    {
        public Guid Id { get; set; }
        public string? OwnerId { get; set; }
        public EShopApplicationUser? Owner { get; set; }
        public virtual ICollection<TicketInShoppingCart>? ProductInShoppingCarts { get; set; }
    }
}
