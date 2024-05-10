using System.ComponentModel.DataAnnotations;

namespace BookingApplication.Models
{
    public class Apartment
    {
        [Key]
        public Guid Id { get; set; }
        [Required]
        public string ApartmentName { get; set; }
        [Required]
        public string City { get; set; }
        [Required]
        public string Description { get; set; }
        [Required]
        public int Price_per_night { get; set; }
        [Required]
        public double Rating { get; set; }
    }
}
