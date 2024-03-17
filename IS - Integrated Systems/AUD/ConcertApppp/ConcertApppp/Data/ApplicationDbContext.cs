using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using ConcertApppp.Models;

namespace ConcertApppp.Data
{
    public class ApplicationDbContext : IdentityDbContext<User>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        public DbSet<Concert> Concert { get; set; } = default!;
        public DbSet<Ticket> Ticket { get; set; } = default!;
    }
}
