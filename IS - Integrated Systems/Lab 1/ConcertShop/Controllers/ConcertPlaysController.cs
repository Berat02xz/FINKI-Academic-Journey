using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using ConcertShop.Data;
using ConcertShop.Models.Domain;

namespace ConcertShop.Controllers
{
    public class ConcertPlaysController : Controller
    {
        private readonly ApplicationDbContext _context;

        public ConcertPlaysController(ApplicationDbContext context)
        {
            _context = context;
        }

        // GET: ConcertPlays
        public async Task<IActionResult> Index()
        {
            return View(await _context.ConcertPlay.ToListAsync());
        }

        // GET: ConcertPlays/Details/5
        public async Task<IActionResult> Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var concertPlay = await _context.ConcertPlay
                .FirstOrDefaultAsync(m => m.Id == id);
            if (concertPlay == null)
            {
                return NotFound();
            }

            return View(concertPlay);
        }

        // GET: ConcertPlays/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: ConcertPlays/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,ConcertName,ConcertPlace,ConcertPrice,ConcertDate")] ConcertPlay concertPlay)
        {
            if (ModelState.IsValid)
            {
                concertPlay.Id = Guid.NewGuid();
                _context.Add(concertPlay);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(concertPlay);
        }

        // GET: ConcertPlays/Edit/5
        public async Task<IActionResult> Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var concertPlay = await _context.ConcertPlay.FindAsync(id);
            if (concertPlay == null)
            {
                return NotFound();
            }
            return View(concertPlay);
        }

        // POST: ConcertPlays/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(Guid id, [Bind("Id,ConcertName,ConcertPlace,ConcertPrice,ConcertDate")] ConcertPlay concertPlay)
        {
            if (id != concertPlay.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(concertPlay);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!ConcertPlayExists(concertPlay.Id))
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
            return View(concertPlay);
        }

        // GET: ConcertPlays/Delete/5
        public async Task<IActionResult> Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var concertPlay = await _context.ConcertPlay
                .FirstOrDefaultAsync(m => m.Id == id);
            if (concertPlay == null)
            {
                return NotFound();
            }

            return View(concertPlay);
        }

        // POST: ConcertPlays/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(Guid id)
        {
            var concertPlay = await _context.ConcertPlay.FindAsync(id);
            if (concertPlay != null)
            {
                _context.ConcertPlay.Remove(concertPlay);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool ConcertPlayExists(Guid id)
        {
            return _context.ConcertPlay.Any(e => e.Id == id);
        }
    }
}
