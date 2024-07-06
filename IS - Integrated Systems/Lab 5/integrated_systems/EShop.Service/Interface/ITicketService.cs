using MusicStore.Domain.Domain;

namespace MusicStore.Service.Interface
{
    public interface ITicketService
    {
        public List<Ticket> GetProducts();
        public Ticket GetProductById(Guid? id);
        public Ticket CreateNewProduct(string userId, Ticket product);
        public Ticket UpdateProduct(Ticket product);
        public Ticket DeleteProduct(Guid id);
    }
}
