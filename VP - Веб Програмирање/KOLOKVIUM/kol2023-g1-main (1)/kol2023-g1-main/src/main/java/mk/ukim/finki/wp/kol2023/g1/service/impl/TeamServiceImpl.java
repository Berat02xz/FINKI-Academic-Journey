package mk.ukim.finki.wp.kol2023.g1.service.impl;

import mk.ukim.finki.wp.kol2023.g1.model.Team;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidTeamIdException;
import mk.ukim.finki.wp.kol2023.g1.repository.PlayerRepository;
import mk.ukim.finki.wp.kol2023.g1.repository.TeamRepository;
import mk.ukim.finki.wp.kol2023.g1.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public TeamServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(InvalidTeamIdException::new);
    }

    @Override
    public List<Team> listAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team create(String name) {
        return teamRepository.save(new Team(name));
    }
}
