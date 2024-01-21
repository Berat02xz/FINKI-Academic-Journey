package mk.ukim.finki.wp.kol2022.g1.service.Imp;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImp implements mk.ukim.finki.wp.kol2022.g1.service.EmployeeService{

    private final EmployeeRepository repository;
    private final SkillImp skillService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeImp(EmployeeRepository repository, SkillImp skillService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.skillService = skillService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Employee> listAll() {
        return repository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return repository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        List<Skill> listskills = new ArrayList<>();

        for (int i = 0; i < skillId.size(); i++) {
            Long id = skillId.get(i);
            Skill skill = this.skillService.findById(id);
            listskills.add(skill);
        }

        return repository.save(new Employee(name, email, passwordEncoder.encode(password), type , listskills , employmentDate));
    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee emp = this.findById(id);
        emp.setName(name);
        emp.setEmail(email);
        emp.setPassword(this.passwordEncoder.encode(password));
        emp.setType(type);
        emp.setEmploymentDate(employmentDate);

        List<Skill> listskills = new ArrayList<>();
        for(int i = 0; i < skillId.size(); i++){
            Long id1 = skillId.get(i);
            Skill skill = this.skillService.findById(id1);
            listskills.add(skill);
        }
        emp.setSkills(listskills);


        return emp;
    }

    @Override
    public Employee delete(Long id) {
        Employee emp = this.findById(id);
        repository.delete(emp);
        return emp;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {
        if (skillId != null && yearsOfService != null) {
            Skill skill = skillService.findById(skillId);
            LocalDate employmentBefore = LocalDate.now().minusYears(yearsOfService);
            return repository.findByEmploymentDateBeforeAndSkillsContaining(employmentBefore, skill);
        } else if (skillId == null && yearsOfService == null) {
            return repository.findAll();
        } else if (skillId != null) {
            Skill skill = skillService.findById(skillId);
            return repository.findBySkillsContaining(skill);
        } else {
            LocalDate employmentBefore = LocalDate.now().minusYears(yearsOfService);
            return repository.findByEmploymentDateBefore(employmentBefore);
        }
    }
}
