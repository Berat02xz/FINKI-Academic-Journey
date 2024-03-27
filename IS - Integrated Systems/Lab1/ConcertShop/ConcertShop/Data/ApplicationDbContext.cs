using ConcertShop.Models.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using ConcertShop.Models.Domain;

namespace ConcertShop.Data
{
    public class ApplicationDbContext : IdentityDbContext<ApplicationUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        
        public DbSet<ConcertShop.Models.Domain.ConcertPlay> ConcertPlay { get; set; }
        public DbSet<ConcertShop.Models.Domain.Ticket> Ticket { get; set; }


            
    }

}
