using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using TheatreApplication.Models;

namespace TheatreApplication.Data
{
    public class ApplicationDbContext : IdentityDbContext<TheatreUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        public DbSet<TheatreApplication.Models.TheatreShow> TheatreShow { get; set; } = default!;
        public DbSet<TheatreApplication.Models.Ticket> Ticket { get; set; } = default!;
    }
}
