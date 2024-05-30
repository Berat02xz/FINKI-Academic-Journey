using EShop.Repository.Interface;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace EShop.Web.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class AdminController : ControllerBase
    {
        private readonly IOrder _orderRepository;

        public AdminController(IOrder orderRepository)
        {
            _orderRepository = orderRepository;
        }


        [HttpGet("GetAllOrders")]
        public IActionResult GetAllOrders()
        {
            return Ok(_orderRepository.GetAllOrders());
        }
    }
}
