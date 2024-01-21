package mk.ukim.finki.wp.kol2022.g3.service.Imp;

import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidInterestIdException;
import mk.ukim.finki.wp.kol2022.g3.repository.InterestRepository;
import mk.ukim.finki.wp.kol2022.g3.service.InterestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestImp implements InterestService {

    private final InterestRepository interestRepository;

    public InterestImp(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    @Override
    public Interest findById(Long id) {
        return interestRepository.findById(id).orElseThrow(InvalidInterestIdException::new);
    }

    @Override
    public List<Interest> listAll() {
        return interestRepository.findAll();
    }

    @Override
    public Interest create(String name) {
        return interestRepository.save(new Interest(name));
    }
}
