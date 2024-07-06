using Microsoft.AspNetCore.Identity;
using MusicStore.Domain.Domain;

namespace MusicStore.Domain.Identity
{
    public class MusicStoreUser : IdentityUser
    {
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public string? Address { get; set; }
        public ShoppingCart? UserCart { get; set; }
        public virtual ICollection<Order>? Orders { get; set; }
    }
}
