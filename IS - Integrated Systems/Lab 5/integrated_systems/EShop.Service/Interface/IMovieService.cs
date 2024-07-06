using MusicStore.Domain.Domain;

namespace MusicStore.Service.Interface
{
    public interface IMovieService
    {
        List<Movie> GetAllMovies();
        Movie GetDetailsForMovie(Guid? id);
        void CreateNewMovie(Movie p);
        void UpdateExistingMovie(Movie p);
        void DeleteMovie(Guid id);
    }
}
