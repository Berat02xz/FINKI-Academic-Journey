using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using BookingApplication.Data;
using BookingApplication.Models;
using System.Security.Claims;

namespace BookingApplication.Controllers
{
    public class BookingListsController : Controller
    {
        private readonly ApplicationDbContext _context;

        public BookingListsController(ApplicationDbContext context)
        {
            _context = context;
        }

        // GET: BookingLists
        public async Task<IActionResult> Index()
        {
            var applicationDbContext = _context.BookingList.Include(b => b.User);
            return View(await applicationDbContext.ToListAsync());
        }

        // GET: BookingLists/Details/5
        public async Task<IActionResult> Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var bookingList = await _context.BookingList
                .Include(b => b.User)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (bookingList == null)
            {
                return NotFound();
            }

            return View(bookingList);
        }

        // GET: BookingLists/Create
        public IActionResult Create()
        {
            ViewData["UserId"] = new SelectList(_context.Users, "Id", "Id");
            return View();
        }

        // POST: BookingLists/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Full_Price,UserId")] BookingList bookingList)
        {
            if (ModelState.IsValid)
            {
                bookingList.Id = Guid.NewGuid();
                _context.Add(bookingList);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["UserId"] = new SelectList(_context.Users, "Id", "Id", bookingList.UserId);
            return View(bookingList);
        }

        // GET: BookingLists/Edit/5
        public async Task<IActionResult> Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var bookingList = await _context.BookingList.FindAsync(id);
            if (bookingList == null)
            {
                return NotFound();
            }
            ViewData["UserId"] = new SelectList(_context.Users, "Id", "Id", bookingList.UserId);
            return View(bookingList);
        }

        // POST: BookingLists/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(Guid id, [Bind("Id,Full_Price,UserId")] BookingList bookingList)
        {
            if (id != bookingList.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(bookingList);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!BookingListExists(bookingList.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            ViewData["UserId"] = new SelectList(_context.Users, "Id", "Id", bookingList.UserId);
            return View(bookingList);
        }

        // GET: BookingLists/Delete/5
        public async Task<IActionResult> Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var bookingList = await _context.BookingList
                .Include(b => b.User)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (bookingList == null)
            {
                return NotFound();
            }

            return View(bookingList);
        }

        // POST: BookingLists/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(Guid id)
        {
            var bookingList = await _context.BookingList.FindAsync(id);
            if (bookingList != null)
            {
                _context.BookingList.Remove(bookingList);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool BookingListExists(Guid id)
        {
            return _context.BookingList.Any(e => e.Id == id);
        }

        //POST: BookingLists/Checkout
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Checkout()
        {
            //Find logged in user authenticated
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);
            if (userId == null)
            {
                return RedirectToAction("Login", "Account"); // Redirect to login if user is not authenticated
            }

            //Create a new booking list
            var bookingList = new BookingList
                {
                    Id = Guid.NewGuid(),
                    UserId = userId,
                    //Add the booking reservations of the user to the booking list
                    BookReservations = _context.BookReservation.Where(b => b.UserId == userId).ToList(),
                    //calculate full price by summing up the prices of all the reservations in the booking list
                    Full_Price = _context.BookReservation.Where(b => b.UserId == userId).Sum(b => b.Reservation.Apartment.Price_per_night * b.Number_Of_Nights)
                };
                _context.Add(bookingList);
            

            //Add the booking list to the database
            _context.BookingList.Add(bookingList);

            

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }
    }
}
