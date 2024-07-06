using MusicStore.Domain.Identity;
using System.ComponentModel.DataAnnotations;

namespace MusicStore.Domain.Domain
{
    public class Ticket : BaseEntity
    {
        public Guid MovieId { get; set; }
        public Movie? Movie { get; set; }

        [Required]
        public double Price { get; set; }

        public virtual MusicStoreUser? CreatedBy { get; set; }
        public virtual ICollection<TicketInShoppingCart>? ProductsInShoppingCart { get; set; }
        public ICollection<TicketInOrder>? ProductInOrders { get; set; }
    }
}
