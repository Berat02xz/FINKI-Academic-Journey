using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using MovieApp.Models;

namespace MovieApp.Data
{
    public class ApplicationDbContext : IdentityDbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        public virtual DbSet<MovieApp.Models.Movie> Movies { get; set; }
        public virtual DbSet<MovieApp.Models.Ticket> Tickets { get; set; }
        public virtual DbSet<MovieApp.Models.TicketInOrder> TicketInOrders { get; set; }
        public virtual DbSet<MovieApp.Models.Order> Orders { get; set; }


    }
}
