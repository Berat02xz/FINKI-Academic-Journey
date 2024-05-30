using EShop.Domain.Identity;
using System.ComponentModel.DataAnnotations;


namespace EShop.Domain.Domain
{
    public class ShoppingCart : BaseEntity
    {
        public string? OwnerId { get; set; }
        public EShopApplicationUser? Owner { get; set; }
        public virtual ICollection<TicketInShoppingCart>? ProductInShoppingCarts { get; set; }
    }
}
