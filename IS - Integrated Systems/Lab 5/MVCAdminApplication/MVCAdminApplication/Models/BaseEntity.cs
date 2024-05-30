using System.ComponentModel.DataAnnotations;

namespace MVCAdminApplication.Models
{
    public class BaseEntity
    {
        [Key]
        public Guid Id { get; set; }
    }
}
