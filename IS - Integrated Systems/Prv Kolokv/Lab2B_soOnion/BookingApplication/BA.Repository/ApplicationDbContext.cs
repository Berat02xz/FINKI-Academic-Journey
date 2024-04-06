using BA.Domain.Domain;
using BA.Domain.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;


namespace BA.Repository
{
    public class ApplicationDbContext : IdentityDbContext<BookingApplicationUser>
    {
        public DbSet<Apartment> Apartments { get; set; }
        public DbSet<Reservation> Reservations { get; set; }
        public DbSet<BookingList> BookingLists { get; set; }
        public DbSet<BookReservation> BookReservations { get; set; }

        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {
        }
    }
}
