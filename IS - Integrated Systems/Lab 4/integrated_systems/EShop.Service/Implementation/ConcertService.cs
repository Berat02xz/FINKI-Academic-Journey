using EShop.Domain.Domain;
using EShop.Repository.Interface;
using Movie_App.Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Movie_App.Service.Implementation
{
    public class ConcertService : IConcertService
    {
        private readonly IRepository<Concert> _concertRepository;

        public ConcertService(IRepository<Concert> concertRepository)
        {
            _concertRepository = concertRepository;
        }
        public void CreateNewConcert(Concert p)
        {
            _concertRepository.Insert(p);
        }

        public void DeleteConcert(Guid id)
        {
            Concert movie = _concertRepository.Get(id);
            _concertRepository.Delete(movie);
        }

        public List<Concert> GetAllConcerts()
        {
            return _concertRepository.GetAll().ToList();
        }

        public Concert GetDetailsForConcert(Guid? id)
        {
            return _concertRepository.Get(id);
        }

        public void UpdateExistingConcert(Concert p)
        {
            _concertRepository.Update(p);
        }
    }
}
