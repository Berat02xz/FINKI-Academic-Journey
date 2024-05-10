using EShop.Domain.Identity;
using System.ComponentModel.DataAnnotations;

namespace EShop.Domain.Domain
{
    public class Ticket : BaseEntity
    {
        public Guid ConcertId { get; set; }
        public Concert? Concert { get; set; }

        [Required]
        public double Price { get; set; }

        [Required]
        public double Rating { get; set; }
        public virtual EShopApplicationUser? CreatedBy { get; set; }
        public virtual ICollection<TicketInShoppingCart>? ProductsInShoppingCart { get; set; }
        public ICollection<TicketInOrder>? ProductInOrders { get; set; }
    }
}
