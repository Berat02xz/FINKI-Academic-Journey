using System.ComponentModel.DataAnnotations;

namespace MusicStore.Domain.Domain
{
    public class BaseEntity
    {
        [Key]
        public Guid Id { get; set; }
    }
}
