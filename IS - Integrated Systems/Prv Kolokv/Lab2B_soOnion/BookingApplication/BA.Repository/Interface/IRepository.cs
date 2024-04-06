using BA.Domain.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Repository.Interface
{
    public interface IRepository<T> where T : BaseEntity
    {
        IEnumerable<T> GetAll();
        T Get(Guid? id);
        void Insert(T entity);
        void Update(T entity);
        void Delete(T entity); 

    }
}
