using System.ComponentModel.DataAnnotations.Schema;
using System.Net.Sockets;

namespace ConcertApppp.Models
{
    public class Concert
    {
        public int Id { get; set; }
        public string ConcertName { get; set; }
        public DateTime ConcertDate { get; set; }
        public int ConcertPrice { get; set; }
        public string ConcertPlace { get; set; }

        // Navigation property for one-to-many relationship with tickets
        public ICollection<Ticket> Tickets { get; set; }
    }
}
