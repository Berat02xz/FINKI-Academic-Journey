using MVCAdminApplication.Models;

namespace MVCAdminApplication.Models
{
    public class Order : BaseEntity
    {
        public string? OwnerId { get; set; }
        public EShopApplicationUser? Owner { get; set; }
        public ICollection<TicketInOrder>? ProductInOrders { get; set; }
    }
}
