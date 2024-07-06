using MusicStore.Domain.Domain;

namespace MusicStore.Domain.DTO
{
    public class ShoppingCartDTO
    {
        public List<TicketInShoppingCart>? AllProducts { get; set; }
        public double TotalPrice { get; set; }
    }
}
