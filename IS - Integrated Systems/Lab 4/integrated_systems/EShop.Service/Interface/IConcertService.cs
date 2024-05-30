using EShop.Domain.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Movie_App.Service.Interface
{
    public interface IConcertService
    {
        List<Concert> GetAllConcerts();
        Concert GetDetailsForConcert(Guid? id);
        void CreateNewConcert(Concert p);
        void UpdateExistingConcert(Concert p);
        void DeleteConcert(Guid id);
    }
}
