using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EShop.Domain.DTO
{
    public class AddToCartDTO
    {
        public Guid SelectedProductId { get; set; }
        public string? SelectedProductName { get; set; }
        public int Quantity { get; set; }
    }
}
