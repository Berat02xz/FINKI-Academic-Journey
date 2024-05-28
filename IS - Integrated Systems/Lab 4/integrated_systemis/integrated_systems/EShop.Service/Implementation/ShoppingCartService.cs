using EShop.Domain.Domain;
using EShop.Domain.DTO;
using EShop.Repository.Interface;
using EShop.Service.Interface;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EShop.Service.Implementation
{
    public class ShoppingCartService : IShoppingCartService
    {
        private readonly IUserRepository _userRepository;
        private readonly IRepository<ShoppingCart> _shoppingCartRepository;
        private readonly IRepository<Ticket> _productRepository;
        private readonly IRepository<Order> _orderRepository;
        private readonly IRepository<TicketInOrder> _productInOrderRepository;

        public ShoppingCartService(IUserRepository userRepository, IRepository<ShoppingCart> shoppingCartRepository, IRepository<Ticket> productRepository, IRepository<Order> orderRepository, IRepository<TicketInOrder> productInOrderRepository)
        {
            _userRepository = userRepository;
            _shoppingCartRepository = shoppingCartRepository;
            _productRepository = productRepository;
            _orderRepository = orderRepository;
            _productInOrderRepository = productInOrderRepository;
        }

        public ShoppingCart AddProductToShoppingCart(string userId, AddToCartDTO model)
        {
            if (userId != null)
            {
                var loggedInUser = _userRepository.Get(userId);

                var userCart = loggedInUser?.UserCart;

                var selectedProduct = _productRepository.Get(model.SelectedProductId);

                if (selectedProduct != null && userCart != null)
                {
                    userCart?.ProductInShoppingCarts?.Add(new TicketInShoppingCart
                    {
                        Ticket = selectedProduct,
                        TicketId = selectedProduct.Id,
                        ShoppingCart = userCart,
                        ShoppingCartId = userCart.Id,
                        Quantity = model.Quantity
                    });

                    return _shoppingCartRepository.Update(userCart);
                }
            }
            return null;
        }

        public bool deleteFromShoppingCart(string userId, Guid? Id)
        {
            if (userId != null)
            {
                var loggedInUser = _userRepository.Get(userId);


                var product_to_delete = loggedInUser?.UserCart?.ProductInShoppingCarts.First(z => z.TicketId == Id);

                loggedInUser?.UserCart?.ProductInShoppingCarts?.Remove(product_to_delete);

                _shoppingCartRepository.Update(loggedInUser.UserCart);

                return true;

            }

            return false;
        }

        public AddToCartDTO getProductInfo(Guid Id)
        {
            var selectedProduct = _productRepository.Get(Id);
            if (selectedProduct != null)
            {
                var model = new AddToCartDTO
                {
                    SelectedProductName = selectedProduct.ConcertId.ToString(),
                    SelectedProductId = selectedProduct.Id,
                    Quantity = 1
                };
                return model;
            }
            return null;
        }

        public ShoppingCartDTO getShoppingCartDetails(string userId)
        {
            if (userId != null && !userId.IsNullOrEmpty())
            {
                var loggedInUser = _userRepository.Get(userId);

                var allProducts = loggedInUser?.UserCart?.ProductInShoppingCarts?.ToList();

                var totalPrice = 0.0;

                foreach (var item in allProducts)
                {
                    totalPrice += Double.Round((item.Quantity * item.Ticket.Price), 2);
                }

                var model = new ShoppingCartDTO
                {
                    AllProducts = allProducts,
                    TotalPrice = totalPrice
                };

                return model;

            }

            return new ShoppingCartDTO
            {
                AllProducts = new List<TicketInShoppingCart>(),
                TotalPrice = 0.0
            };
        }

        public bool orderProducts(string userId)
        {
            if (userId != null)
            {
                var loggedInUser = _userRepository.Get(userId);

                var userShoppingCart = loggedInUser.UserCart;

                Order order = new Order
                {
                    Id = Guid.NewGuid(),
                    OwnerId = userId,
                    Owner = loggedInUser
                };

                _orderRepository.Insert(order);

                List<TicketInOrder> productInOrder = new List<TicketInOrder>();

                var lista = userShoppingCart.ProductInShoppingCarts.Select(
                    x => new TicketInOrder
                    {
                        Id = Guid.NewGuid(),
                        TicketId = x.Ticket.Id,
                        OrderedProduct = x.Ticket,
                        OrderId = order.Id,
                        Order = order,
                        Quantity = x.Quantity
                    }
                    ).ToList();

                productInOrder.AddRange(lista);

                foreach (var product in productInOrder)
                {
                    _productInOrderRepository.Insert(product);
                }

                loggedInUser.UserCart.ProductInShoppingCarts.Clear();
                _userRepository.Update(loggedInUser);
                return true;
            }
            return false;
        }
    }
}
