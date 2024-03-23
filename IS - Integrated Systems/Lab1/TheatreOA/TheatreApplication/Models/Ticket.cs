using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace TheatreApplication.Models
{
    public class Ticket
    {
        [Key]
        public Guid Id { get; set; }
        public int Price { get; set; }



        [ForeignKey("User")]
        public string? UserId { get; set; }
        public TheatreUser? User { get; set; }



        [ForeignKey("Show")]
        public Guid? ShowId { get; set; }
        public TheatreShow? Show { get; set; }
    }

}
