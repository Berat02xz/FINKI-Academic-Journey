using MusicStore.Domain.Identity;

namespace MusicStore.Domain.Domain
{
    public class ShoppingCart : BaseEntity
    {
        public string? OwnerId { get; set; }
        public MusicStoreUser? Owner { get; set; }
        public virtual ICollection<TicketInShoppingCart>? ProductInShoppingCarts { get; set; }
    }
}
