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
    public class OrderRepository : IOrder
    {
        private readonly IRepository<Order> _orderRepository;
        private DbSet<Order> _dbSet;
        private readonly ApplicationDbContext _context;

        public OrderRepository(IRepository<Order> orderRepository, ApplicationDbContext context)
        {
            _orderRepository = orderRepository;
            _context = context;
            _dbSet = _context.Set<Order>();
        }

        public List<Order> GetAllOrders()
        {
            return _dbSet
                .Include(x => x.Owner)
                .Include(x => x.ProductInOrders)
                .Include("ProductInOrders.OrderedProduct")
                .ToListAsync().Result;
        }
    }
}
