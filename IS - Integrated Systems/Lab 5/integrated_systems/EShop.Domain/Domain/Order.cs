using MusicStore.Domain.Identity;

namespace MusicStore.Domain.Domain
{
    public class Order : BaseEntity
    {
        public string? OwnerId { get; set; }
        public MusicStoreUser? Owner { get; set; }
        public ICollection<TicketInOrder>? ProductInOrders { get; set; }
    }
}
