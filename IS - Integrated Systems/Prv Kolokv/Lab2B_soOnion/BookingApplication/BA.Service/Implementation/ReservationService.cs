using BA.Domain.Domain;
using BA.Repository.Interface;
using BA.Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Service.Implementation
{
    public class ReservationService : IReservationService
    {
        private readonly IRepository<Apartment> _apartmentRepository;
        private readonly IRepository<Reservation> _reservationRepository;
        private readonly IRepository<BookReservation> _bookReservationRepository;
        private readonly IUserRepository _userRepository;

        public ReservationService(IRepository<Apartment> apartmentRepository, IRepository<Reservation> reservationRepository, IRepository<BookReservation> bookReservationRepository, IUserRepository userRepository)
        {
            _apartmentRepository = apartmentRepository;
            _reservationRepository = reservationRepository;
            _bookReservationRepository = bookReservationRepository;
            _userRepository = userRepository;
        }

        public void CreateNewReservation(Reservation p)
        {
            _reservationRepository.Insert(p);
        }

        public void DeleteReservation(Guid id)
        {
            var r = _reservationRepository.Get(id);
            _reservationRepository.Delete(r);
        }

        public List<Reservation> GetAllReservations()
        {
            return _reservationRepository.GetAll().ToList();
        }

        public Reservation GetDetailsForReservation(Guid? id)
        {
            var r = _reservationRepository.Get(id);
            return r;
        }

        public void UpdateExistingReservation(Reservation p)
        {
            _reservationRepository.Update(p);
        }
    }
}
