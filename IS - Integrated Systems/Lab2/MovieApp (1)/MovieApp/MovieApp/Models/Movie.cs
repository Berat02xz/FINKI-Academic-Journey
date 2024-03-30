using System.ComponentModel.DataAnnotations;

namespace MovieApp.Models
{
    public class Movie
    {
        [Key]
        public Guid Id { get; set; }
        [Required]
        public string MovieName { get; set; }
        [Required]
        public string MovieDescription { get; set; }
        [Required]
        public string MovieImage { get; set; }
        [Required]
        public double Rating { get; set; }

        public virtual ICollection<Ticket>? Tickets { get; set;}

    }
}
