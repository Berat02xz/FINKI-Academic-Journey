using BA.Domain.Domain;

namespace BA.Domain.DTO
{
    public class BookingListDTO
    {
        public List<BookReservation> AllReservations { get; set; }
        public double TotalPrice { get; set; }
    }
}
