using System.ComponentModel.DataAnnotations.Schema;

namespace ConcertApppp.Models
{
    public class Ticket
    {
        public int Id { get; set; }
        public int ConcertId { get; set; }
        public Concert Concert { get; set; }
        public string TicketType { get; set; }
        public int Price { get; set; }
        public int NumberOfPeople { get; set; }

        // Foreign key and navigation property for many-to-one relationship with user
       public string UserId { get; set; }
       public User User { get; set; } 
    }
}
