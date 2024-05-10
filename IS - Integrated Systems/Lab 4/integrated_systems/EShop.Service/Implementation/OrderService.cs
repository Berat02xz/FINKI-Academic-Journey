using EShop.Domain.Domain;
using EShop.Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EShop.Service.Implementation
{
    public class OrderService : IOrderService
    {
        private readonly IOrderService _orderService;

        public OrderService(IOrderService orderService)
        {
            _orderService = orderService;
        }
        public List<Order> GetAllOrders()
        {
            return _orderService.GetAllOrders();
        }

        public Order GetDetailsForOrder(BaseEntity id)
        {
            return _orderService.GetDetailsForOrder(id);
        }
    }
}
