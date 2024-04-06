using BA.Domain.Domain;
using BA.Domain.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Service.Interface
{
    public interface IBookingListService
    {
        BookingListDTO getBookingListInfo(string userId);   
        bool deleteApartmentFromList(string userId, Guid reservationId);
        bool order(string userId);
        bool AddToListConfirmed(BookReservation model, string userId);
    }
}
