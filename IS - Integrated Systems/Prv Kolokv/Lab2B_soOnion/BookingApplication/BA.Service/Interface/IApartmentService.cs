using BA.Domain.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Service.Interface
{
    public interface IApartmentService
    {
        List<Apartment> GetAllApartments();
        Apartment GetDetailsForApartment(Guid? id);
        void CreateNewApartment(Apartment p);
        void UpdateExistingApartment(Apartment p);
        void DeleteApartment(Guid id);
    }
}
