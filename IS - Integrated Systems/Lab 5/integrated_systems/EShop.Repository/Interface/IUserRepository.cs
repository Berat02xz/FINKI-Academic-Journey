using MusicStore.Domain.Identity;

namespace MusicStore.Repository.Interface
{
    public interface IUserRepository
    {
        IEnumerable<MusicStoreUser> GetAll();
        MusicStoreUser Get(string id);
        void Insert(MusicStoreUser entity);
        void Update(MusicStoreUser entity);
        void Delete(MusicStoreUser entity);
    }
}
