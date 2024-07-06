using EShop.Domain.Domain;
using EShop.Repository.Interface;
using EShop.Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace EShop.Service.Implementation
{
    public class TicketService : ITicketService
    {
        private readonly IRepository<Ticket> _productRepository;
        private readonly IUserRepository _userRepository;

        public TicketService(IRepository<Ticket> productRepository, IUserRepository userRepository)
        {
            _productRepository = productRepository;
            _userRepository = userRepository;
        }


        public Ticket CreateNewProduct(string userId, Ticket product)
        {
            var createdBy = _userRepository.Get(userId);
            product.CreatedBy = createdBy;
            return _productRepository.Insert(product);
        }

        public Ticket DeleteProduct(Guid id)
        {
            var product_to_delete = this.GetProductById(id);
            return _productRepository.Delete(product_to_delete);
        }

        public Ticket GetProductById(Guid? id)
        {
            return _productRepository.Get(id);
        }

        public List<Ticket> GetProducts()
        {
            return _productRepository.GetAll().ToList();
        }

        public Ticket UpdateProduct(Ticket product)
        {
           return _productRepository.Update(product);
        }
    }
}
