using Microsoft.AspNetCore.Mvc;
using MusicStore.Service.Interface;
using System.Security.Claims;

namespace MusicStore.Web.Controllers
{
    public class ShoppingCartsController : Controller
    {
        private readonly IShoppingCartService _shoppingCartService;

        public ShoppingCartsController(IShoppingCartService shoppingCartService)
        {
            _shoppingCartService = shoppingCartService;
        }

        // GET: ShoppingCarts
        public async Task<IActionResult> Index()
        {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier) ?? null;

            return View(_shoppingCartService.getShoppingCartDetails(userId??"")); 
        }

        // GET: ShoppingCarts/Details/5
     
        // GET: ShoppingCarts/Delete/5
        public async Task<IActionResult> DeleteProductFromShoppingCart(Guid? productId)
        {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier) ?? null;

            var result = _shoppingCartService.deleteFromShoppingCart(userId, productId);

            return RedirectToAction("Index", "ShoppingCarts");
        }

        public async Task<IActionResult> Order()
        {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier) ?? null;

            var result = _shoppingCartService.orderProducts(userId??"");

            return RedirectToAction("Index", "ShoppingCarts");
        }

    }
}
