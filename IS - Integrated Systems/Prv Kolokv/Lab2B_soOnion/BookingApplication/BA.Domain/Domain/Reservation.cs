using BA.Domain.Identity;
using System.ComponentModel.DataAnnotations;

namespace BA.Domain.Domain
{
    public class Reservation : BaseEntity
    {
        [Required]
        public DateTime Check_in_date { get; set; }
        public Guid ApartmentId { get; set; }
        public Apartment? Apartment { get; set; }
        public virtual BookingApplicationUser? User { get; set; }
        public virtual ICollection<BookReservation>? BookedReservation { get; set; }

    }
}
