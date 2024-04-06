using BA.Domain.Identity;

namespace BA.Domain.Domain
{
    public class BookingList : BaseEntity
    {
        public string OwnerId { get; set; }
        public BookingApplicationUser Owner { get; set; }
        public ICollection<BookReservation>? Reservations { get; set; }
    }
}
