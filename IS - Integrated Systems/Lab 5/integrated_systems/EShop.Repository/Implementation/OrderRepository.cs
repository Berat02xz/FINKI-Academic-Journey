using EShop.Domain.Domain;
using EShop.Repository.Interface;
using Microsoft.EntityFrameworkCore;

namespace EShop.Repository.Implementation
{
    public class OrderRepository : IOrderRepository
    {
        private readonly ApplicationDbContext _context;
        private DbSet<Order> _orders;
        public OrderRepository(ApplicationDbContext context)
        {
            _context = context;
            _orders = context.Set<Order>();
        }
        public List<Order> GetAllOrders()
        {
            return _orders
                .Include(z => z.ProductInOrders)
                .Include(z => z.Owner)
                .Include("ProductInOrders.OrderedProduct")
                .ToList();
        }

        public Order GetDetailsForOrder(BaseEntity model)
        {
            return _orders
                .Include(z => z.ProductInOrders)
                    .ThenInclude(z => z.OrderedProduct)
                        .ThenInclude(z => z.Movie)
                .Include(z => z.Owner)
                .Include("ProductInOrders.OrderedProduct")
                .SingleOrDefaultAsync(z => z.Id == model.Id).Result;
        }
    }
}
