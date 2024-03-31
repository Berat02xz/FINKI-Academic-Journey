using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using MovieApp.Data;
using MovieApp.Models;

namespace MovieApp.Controllers
{
    public class TicketInOrdersController : Controller
    {
        private readonly ApplicationDbContext _context;

        public TicketInOrdersController(ApplicationDbContext context)
        {
            _context = context;
        }

        // GET: TicketInOrders
        public async Task<IActionResult> Index()
        {
            var applicationDbContext = _context.TicketInOrders.Include(t => t.Ticket).Include(t => t.User);
            return View(await applicationDbContext.ToListAsync());
        }

        // GET: TicketInOrders/Details/5
        public async Task<IActionResult> Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var ticketInOrder = await _context.TicketInOrders
                .Include(t => t.Ticket)
                .Include(t => t.User)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (ticketInOrder == null)
            {
                return NotFound();
            }

            return View(ticketInOrder);
        }

        // GET: TicketInOrders/Create
        public IActionResult Create()
        {
            ViewData["TicketId"] = new SelectList(_context.Tickets, "Id", "Id");
            ViewData["UserId"] = new SelectList(_context.Set<EShopApplicationUser>(), "Id", "Id");
            return View();
        }

        // POST: TicketInOrders/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,TicketId,UserId,Quantity")] TicketInOrder ticketInOrder)
        {
            if (ModelState.IsValid)
            {
                ticketInOrder.Id = Guid.NewGuid();
                _context.Add(ticketInOrder);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["TicketId"] = new SelectList(_context.Tickets, "Id", "Id", ticketInOrder.TicketId);
            ViewData["UserId"] = new SelectList(_context.Set<EShopApplicationUser>(), "Id", "Id", ticketInOrder.UserId);
            return View(ticketInOrder);
        }

        // GET: TicketInOrders/Edit/5
        public async Task<IActionResult> Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var ticketInOrder = await _context.TicketInOrders.FindAsync(id);
            if (ticketInOrder == null)
            {
                return NotFound();
            }
            ViewData["TicketId"] = new SelectList(_context.Tickets, "Id", "Id", ticketInOrder.TicketId);
            ViewData["UserId"] = new SelectList(_context.Set<EShopApplicationUser>(), "Id", "Id", ticketInOrder.UserId);
            return View(ticketInOrder);
        }

        // POST: TicketInOrders/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(Guid id, [Bind("Id,TicketId,UserId,Quantity")] TicketInOrder ticketInOrder)
        {
            if (id != ticketInOrder.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(ticketInOrder);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!TicketInOrderExists(ticketInOrder.Id))
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
            ViewData["TicketId"] = new SelectList(_context.Tickets, "Id", "Id", ticketInOrder.TicketId);
            ViewData["UserId"] = new SelectList(_context.Set<EShopApplicationUser>(), "Id", "Id", ticketInOrder.UserId);
            return View(ticketInOrder);
        }

        // GET: TicketInOrders/Delete/5
        public async Task<IActionResult> Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var ticketInOrder = await _context.TicketInOrders
                .Include(t => t.Ticket)
                .Include(t => t.User)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (ticketInOrder == null)
            {
                return NotFound();
            }

            return View(ticketInOrder);
        }

        // POST: TicketInOrders/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(Guid id)
        {
            var ticketInOrder = await _context.TicketInOrders.FindAsync(id);
            if (ticketInOrder != null)
            {
                _context.TicketInOrders.Remove(ticketInOrder);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool TicketInOrderExists(Guid id)
        {
            return _context.TicketInOrders.Any(e => e.Id == id);
        }
    }
}
