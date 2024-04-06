using BA.Domain.Domain;
using BA.Service.Interface;
using BA.Repository.Interface;

namespace BA.Service.Implementation
{
    public class ApartmentService : IApartmentService
    {
        private readonly IRepository<Apartment> _apartmentRepository;
        private readonly IUserRepository _userRepository;

        public ApartmentService(IRepository<Apartment> apartmentRepository, IUserRepository userRepository)
        {
            _apartmentRepository = apartmentRepository;
            _userRepository = userRepository;
        }
        public void CreateNewApartment(Apartment p)
        {
           _apartmentRepository.Insert(p);
        }
        public void DeleteApartment(Guid id)
        {
            var p = _apartmentRepository.Get(id);
            _apartmentRepository.Delete(p);
        }
        public List<Apartment> GetAllApartments()
        {
            return _apartmentRepository.GetAll().ToList();
        }
        public Apartment GetDetailsForApartment(Guid? id)
        {
            return _apartmentRepository.Get(id);
        }
        public void UpdateExistingApartment(Apartment p)
        {
            _apartmentRepository.Update(p);
        }
    }
}
