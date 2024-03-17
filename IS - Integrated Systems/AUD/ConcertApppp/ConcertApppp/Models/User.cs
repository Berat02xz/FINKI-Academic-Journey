using Microsoft.AspNetCore.Identity;

namespace ConcertApppp.Models
{
    public class User : IdentityUser
    {
        public string? firstname { get; set; }
        public string? lastname { get; set; }
        public string? billingadress { get; set; }
    }
}
