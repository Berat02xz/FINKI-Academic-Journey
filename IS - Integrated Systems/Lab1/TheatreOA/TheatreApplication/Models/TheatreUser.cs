using Microsoft.AspNetCore.Identity;
using System.ComponentModel.DataAnnotations;

namespace TheatreApplication.Models
{
    public class TheatreUser : IdentityUser
    {
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public string? Address { get; set; }
    }
}
