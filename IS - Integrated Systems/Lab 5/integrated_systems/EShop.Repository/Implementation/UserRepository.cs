using MusicStore.Repository.Interface;
using Microsoft.EntityFrameworkCore;
using MusicStore.Domain.Identity;

namespace MusicStore.Repository.Implementation
{
    public class UserRepository : IUserRepository
    {
        private readonly ApplicationDbContext context;
        private DbSet<MusicStoreUser> entities;
        string errorMessage = string.Empty;

        public UserRepository(ApplicationDbContext context)
        {
            this.context = context;
            entities = context.Set<MusicStoreUser>();
        }
        public IEnumerable<MusicStoreUser> GetAll()
        {
            return entities.AsEnumerable();
        }

        public MusicStoreUser Get(string id)
        {
            var strGuid = id.ToString();
            return entities
                .Include(z => z.UserCart)
                .Include(z => z.UserCart.ProductInShoppingCarts)
                .Include("UserCart.ProductInShoppingCarts.Ticket")
                .First(s => s.Id == strGuid);
        }
        public void Insert(MusicStoreUser entity)
        {
            if (entity == null)
            {
                throw new ArgumentNullException("entity");
            }
            entities.Add(entity);
            context.SaveChanges();
        }

        public void Update(MusicStoreUser entity)
        {
            if (entity == null)
            {
                throw new ArgumentNullException("entity");
            }
            entities.Update(entity);
            context.SaveChanges();
        }

        public void Delete(MusicStoreUser entity)
        {
            if (entity == null)
            {
                throw new ArgumentNullException("entity");
            }
            entities.Remove(entity);
            context.SaveChanges();
        }
    }
}
