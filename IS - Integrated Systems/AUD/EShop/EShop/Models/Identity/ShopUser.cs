using Microsoft.AspNetCore.Identity;

namespace EShop.Models.Identity
{
    public class ShopUser : IdentityUser
    {
       public string FirstName { get; set; }
       public string LastName { get; set; }
       public string FullName => $"{FirstName} {LastName}";
       public string Address { get; set; }
    }
}
