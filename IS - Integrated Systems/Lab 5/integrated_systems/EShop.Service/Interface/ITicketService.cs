using EShop.Domain.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EShop.Service.Interface
{
    public interface ITicketService
    {
        public List<Ticket> GetProducts();
        public Ticket GetProductById(Guid? id);
        public Ticket CreateNewProduct(string userId, Ticket product);
        public Ticket UpdateProduct(Ticket product);
        public Ticket DeleteProduct(Guid id);
    }
}
