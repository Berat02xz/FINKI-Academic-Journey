package mk.ukim.finki.wp.kol2022.g1.config;


import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import mk.ukim.finki.wp.kol2022.g1.service.SkillService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private final SkillService skillService;

    private final EmployeeService service;

    public DataInitializer(SkillService skillService, EmployeeService service) {
        this.skillService = skillService;
        this.service = service;
    }

    private EmployeeType randomizeEventType(int i) {
        if (i % 3 == 0) return EmployeeType.CONSULTANT;
        else if (i % 3 == 1) return EmployeeType.ADMIN;
        return EmployeeType.REGULAR;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            this.skillService.create("Skill: " + i);
        }

        List<Skill> skills = this.skillService.listAll();
        for (int i = 1; i < 11; i++) {
            this.service.create(
                    "Employee: " + i,
                    "employee." + i + "@wp.finki.ukim.mk",
                    "emp" + i,
                    this.randomizeEventType(i),
                    Stream.of(skills.get((i - 1) % 5).getId(), skills.get((i + 1) % 5).getId()).collect(Collectors.toList()),
                    LocalDate.now().minusYears((i + 1) / 2)
            );
        }
    }
}
