using EShop.Domain.Domain;
using EShop.Repository.Interface;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EShop.Repository.Implementation
{
    public class OrderRepository : IOrderRepository
    {
        private readonly ApplicationDbContext _context;
        private DbSet<Order> _entity;

        public OrderRepository(ApplicationDbContext context)
        {
            _context = context;
            _entity = context.Set<Order>();
        }

        public List<Order> GetAllOrders()
        {
            return _entity
                .Include(x => x.Owner)
                .Include(x => x.ProductInOrders)
                .Include("ProductsInOrder.Product")
                .ToList();
        }

        public Order GetDetailsForOrder(BaseEntity id)
        {

#pragma warning disable CS8603 // Possible null reference return.
            return _entity
                .Include(x => x.Owner)
                .Include(x => x.ProductInOrders)
                .Include("ProductsInOrder.Product")
                .SingleOrDefaultAsync(x => x.Id == id.Id).Result;
#pragma warning restore CS8603 // Possible null reference return.

        }
    }
}
