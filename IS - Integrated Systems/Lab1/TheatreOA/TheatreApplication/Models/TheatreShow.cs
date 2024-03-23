using System.ComponentModel.DataAnnotations;

namespace TheatreApplication.Models
{
    public class TheatreShow
    {
        [Key]
        public Guid ShowId { get; set; }
        public string? Title { get; set; }
        public string? Description { get; set; }
        public string? Place { get; set; }
        public DateTime? Date { get; set; }
        public string? ImageURL { get; set; }
    }
}
