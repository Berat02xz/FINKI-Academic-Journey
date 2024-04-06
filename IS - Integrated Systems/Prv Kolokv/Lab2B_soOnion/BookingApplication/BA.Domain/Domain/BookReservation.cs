using System.ComponentModel.DataAnnotations;
using System.Net.Sockets;

namespace BA.Domain.Domain
{
    public class BookReservation : BaseEntity
    {
        public Guid ReservationId { get; set; }
        public Reservation? Reservation { get; set; }
        public Guid BookingListId { get; set; }
        public BookingList? BookingList { get; set; }
        public int Number_Of_Nights { get; set; }
    }
}
