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
    public class MovieService : IMovieService
    {
        private readonly IRepository<Movie> _movieRepository;

        public MovieService(IRepository<Movie> movieRepository)
        {
            _movieRepository = movieRepository;
        }
        public void CreateNewMovie(Movie p)
        {
            _movieRepository.Insert(p);
        }

        public void DeleteMovie(Guid id)
        {
            Movie movie = _movieRepository.Get(id);
            _movieRepository.Delete(movie);
        }

        public List<Movie> GetAllMovies()
        {
            return _movieRepository.GetAll().ToList();
        }

        public Movie GetDetailsForMovie(Guid? id)
        {
            return _movieRepository.Get(id);
        }

        public void UpdateExistingMovie(Movie p)
        {
            _movieRepository.Update(p);
        }
    }
}
