using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace MVCAdminApplication.Models
{
    public class Movie : BaseEntity
    {
        [Required]
        public string MovieName { get; set; }
        [Required]
        public string MovieDescription { get; set; }
        [Required]
        public string MovieImage { get; set; }
        [Required]
        public double Rating { get; set; }

        public virtual ICollection<Ticket>? Tickets { get; set; }
    }
}
