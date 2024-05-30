
namespace MVCAdminApplication.Models
{
    public class ShoppingCart : BaseEntity
    {
        public string? OwnerId { get; set; }
        public EShopApplicationUser? Owner { get; set; }
        public virtual ICollection<TicketInShoppingCart>? ProductInShoppingCarts { get; set; }
    }
}
