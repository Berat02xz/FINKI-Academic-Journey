using Microsoft.AspNetCore.Identity;

namespace WebApplication1.Models.Identity
{
    public class TopUser : IdentityUser
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string address { get; set; }
        public string email { get; set; }

    }
}