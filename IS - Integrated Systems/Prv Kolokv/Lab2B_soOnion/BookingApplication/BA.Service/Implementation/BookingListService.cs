using BA.Domain.Domain;
using BA.Domain.DTO;
using BA.Repository.Interface;
using BA.Service.Interface;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;

namespace BA.Service.Implementation
{
    public class BookingListService : IBookingListService
    {
        public readonly IRepository<BookingList> _bookingListRepository;
        private readonly IRepository<Reservation> _reservationRepository;
        private readonly IRepository<Apartment> _apartmentRepository;
        private readonly IRepository<BookReservation> _bookReservationRepository;
        private readonly IUserRepository _userRepository;

        public BookingListService(IRepository<BookingList> bookingListRepository, IRepository<Apartment> apartmentRepository, IRepository<BookReservation> bookReservationRepository, IUserRepository userRepository, IRepository<Reservation> reservationRepository)
        {
            _bookingListRepository = bookingListRepository;
            _apartmentRepository = apartmentRepository;
            _bookReservationRepository = bookReservationRepository;
            _userRepository = userRepository;
            _reservationRepository = reservationRepository;
        }

        public bool AddToListConfirmed(BookReservation model, string userId)
        {
            var loggedInUser = _userRepository.Get(userId);
            var userList = loggedInUser.BookingList;

            if (userList == null)
            {
                userList = new BookingList
                {
                    Id = Guid.NewGuid(),
                    OwnerId = userId,
                    Owner = loggedInUser
                };
            }
            if(userList.Reservations == null)
            {
                userList.Reservations = new List<BookReservation>();
            }
            BookReservation br = new BookReservation
            {
                Id = Guid.NewGuid(),
                BookingListId = userList.Id,
                BookingList = userList,
                ReservationId = model.Id,
                Reservation = model.Reservation,
                Number_Of_Nights = model.Number_Of_Nights
            };
            
            userList.Reservations.Add(br);
            _bookReservationRepository.Insert(br);
            _bookingListRepository.Update(userList);
            return true;
        }

        public bool deleteApartmentFromList(string userId, Guid reservationId)
        {
            if(reservationId != null)
            {
                var loggedInUser = _userRepository.Get(userId);
                var userList = loggedInUser.BookingList;
                var res = userList.Reservations.Where(z => z.ReservationId == reservationId).FirstOrDefault();
                userList.Reservations.Remove(res);
                _bookReservationRepository.Delete(res);
                _bookingListRepository.Update(userList);
                return true;
            }
            return false;
        }

        public BookingListDTO getBookingListInfo(string userId)
        {
            var loggedInUser = _userRepository.Get(userId);
            var userList = loggedInUser.BookingList;
            

            if (userList == null)
            {
                userList = new BookingList
                {
                    Id = Guid.NewGuid(),
                    OwnerId = userId,
                    Owner = loggedInUser
                };
            }
            if(userList.Reservations == null)
            {
                userList.Reservations = new List<BookReservation>();
            }
            var totalPrice = 0.0;

            var allReservations = userList.Reservations.ToList();

            foreach (var item in allReservations)
            {
                var reservation = item.Reservation;
                var apartmentId = item.Reservation.ApartmentId;
                var apartment = _apartmentRepository.Get(apartmentId);
                    
                var apartment_price = apartment.Price_per_night;

                totalPrice += Double.Round((item.Number_Of_Nights * apartment_price), 2);
            }
            

            //var totalPrice = allReservations.Select(x=>(x.Reservation.Apartment.Price_per_night*x.Number_Of_Nights)).Sum();

            BookingListDTO dto = new BookingListDTO
            {
                AllReservations = allReservations,
                TotalPrice = totalPrice
            };
            return dto;
        }

        public bool order(string userId)
        {
            if(userId != null)
            {
                var loggedInUser = _userRepository.Get(userId);
                var userList = loggedInUser?.BookingList;

                BookingList list = new BookingList
                {
                    Id = Guid.NewGuid(),
                    OwnerId = userId,
                    Owner = loggedInUser
                };
                List<BookReservation> productsInShoppingCart = new List<BookReservation>();

                var rez = userList.Reservations.Select(
                            z => new BookReservation
                            {
                                Id = Guid.NewGuid(),
                                ReservationId = z.Reservation.Id,
                                Reservation = z.Reservation,
                                BookingListId = list.Id,
                                BookingList = list,
                                Number_Of_Nights = z.Number_Of_Nights
                            }).ToList();
                productsInShoppingCart.AddRange(rez);

                foreach (var product in productsInShoppingCart)
                {
                    _bookReservationRepository.Insert(product);
                }
                loggedInUser.BookingList.Reservations.Clear();
                _userRepository.Update(loggedInUser);

                return true;
            }
            return false;
        }
    }
}
