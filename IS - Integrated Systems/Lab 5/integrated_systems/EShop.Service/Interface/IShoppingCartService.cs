using MusicStore.Domain.Domain;
using MusicStore.Domain.DTO;

namespace MusicStore.Service.Interface
{
    public interface IShoppingCartService
    {
        ShoppingCart AddProductToShoppingCart(string userId, AddToCartDTO model);
        AddToCartDTO getProductInfo(Guid Id);
        ShoppingCartDTO getShoppingCartDetails(string userId);
        Boolean deleteFromShoppingCart(string userId,  Guid? Id);
        Boolean orderProducts(string userId);
    }
}
