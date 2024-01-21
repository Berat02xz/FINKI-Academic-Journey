package mk.ukim.finki.wp.kol2023.g2.service.impl;

import mk.ukim.finki.wp.kol2023.g2.model.Director;
import mk.ukim.finki.wp.kol2023.g2.model.exceptions.InvalidDirectorIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements mk.ukim.finki.wp.kol2023.g2.service.DirectorService {

    private final mk.ukim.finki.wp.kol2023.g2.repository.MovieRepository movieRepository;
    private final mk.ukim.finki.wp.kol2023.g2.repository.DirectorRepository directorRepository;

    public DirectorServiceImpl(mk.ukim.finki.wp.kol2023.g2.repository.MovieRepository movieRepository, mk.ukim.finki.wp.kol2023.g2.repository.DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }


    @Override
    public Director findById(Long id) {
        return directorRepository.findById(id).orElseThrow(InvalidDirectorIdException::new);
    }

    @Override
    public List<Director> listAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director create(String name) {
        return directorRepository.save(new Director(name));
    }
}
