using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using BookingApplication.Models;
using System.Security.Claims;

using Microsoft.CodeAnalysis;
using BA.Service.Interface;

namespace BookingApplication.Controllers
{
    public class BookingListsController : Controller
    {
        private readonly IBookingListService _bookingListService;

        public BookingListsController(IBookingListService bookingListService)
        {
            _bookingListService = bookingListService;
        }

        // GET: BookingLists
        public IActionResult Index()
        {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);
            var dto = _bookingListService.getBookingListInfo(userId);
            return View(dto);
        }

        public IActionResult DeleteFromList(Guid id)
        {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);
            _bookingListService.deleteApartmentFromList(userId, id);
            return RedirectToAction("Index");
        }

        public IActionResult BookNow()
        {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);

            var result = _bookingListService.order(userId);

            return RedirectToAction("Index", "BookingLists");

        }

    }
}
