using System.ComponentModel.DataAnnotations;

namespace BookingApplication.Models
{
    public class BookingList
    {
        [Key]
        public Guid Id { get; set; }

        
        public ICollection<BookReservation>? BookReservations { get; set; }

        [Required]
        public int Full_Price { get; set; }

        public BookingApplicationUser? User { get; set; }
        public string? UserId { get; set; }
    }
}
