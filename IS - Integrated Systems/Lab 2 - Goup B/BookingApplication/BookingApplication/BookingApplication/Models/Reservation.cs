using System.ComponentModel.DataAnnotations;

namespace BookingApplication.Models
{
    public class Reservation
    {
        [Key]
        public Guid Id { get; set; }
        [Required]
        public DateTime Check_in_date { get; set; }
        public Guid ApartmentId { get; set; }
        public Apartment? Apartment { get; set; }
        public virtual BookingApplicationUser? User { get; set; }
        public string? UserId { get; set; }
    }
}
