package mk.ukim.finki.wp.kol2022.g1.service.Imp;

import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidSkillIdException;
import mk.ukim.finki.wp.kol2022.g1.repository.SkillRepository;
import mk.ukim.finki.wp.kol2022.g1.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillImp implements SkillService {
    private final SkillRepository repository;

    public SkillImp(SkillRepository repository) {
        this.repository = repository;
    }

    @Override
    public Skill findById(Long id) {
        return repository.findById(id).orElseThrow(InvalidSkillIdException::new);
    }

    @Override
    public List<Skill> listAll() {
        return repository.findAll();
    }

    @Override
    public Skill create(String name) {
        return repository.save(new Skill(name));
    }
}
