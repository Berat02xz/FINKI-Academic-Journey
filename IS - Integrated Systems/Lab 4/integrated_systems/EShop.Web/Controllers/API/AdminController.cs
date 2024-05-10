using EShop.Domain.Domain;
using EShop.Domain.Identity;
using EShop.Service.Interface;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

namespace EShop.Web.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class AdminController : Controller
    {

        private readonly IOrderService _orderService;
        private readonly UserManager<EShopApplicationUser> _userManager;

        public AdminController(IOrderService orderService, UserManager<EShopApplicationUser> userManager)
        {
            _orderService = orderService;
            _userManager = userManager;
        }

        [HttpGet("[action]")]
        public List<Order> GetAllOrders()
        {
            return this._orderService.GetAllOrders();
        }

        [HttpGet("[action]")]
        public Order GetOrderDetails(BaseEntity id)
        {
            return this._orderService.GetDetailsForOrder(id);
        }
 


    }
}
