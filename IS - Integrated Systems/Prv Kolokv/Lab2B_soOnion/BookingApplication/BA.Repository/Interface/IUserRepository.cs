using BA.Domain.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Repository.Interface
{
    public interface IUserRepository
    {
        IEnumerable<BookingApplicationUser> GetAll();
        BookingApplicationUser Get(string? id);
        void Insert(BookingApplicationUser entity);
        void Update(BookingApplicationUser entity);
        void Delete(BookingApplicationUser entity);
    }
}
