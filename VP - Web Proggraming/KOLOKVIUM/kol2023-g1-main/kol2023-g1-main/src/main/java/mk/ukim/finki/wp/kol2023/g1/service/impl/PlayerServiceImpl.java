package mk.ukim.finki.wp.kol2023.g1.service.impl;

import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import mk.ukim.finki.wp.kol2023.g1.model.Team;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidPlayerIdException;
import mk.ukim.finki.wp.kol2023.g1.repository.PlayerRepository;
import mk.ukim.finki.wp.kol2023.g1.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamServiceImpl teamService;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamServiceImpl teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    @Override
    public List<Player> listAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(Long id) {
        return playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
    }

    @Override
    public Player create(String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        Team teamid = teamService.findById(team);
        return playerRepository.save(new Player(name, bio, pointsPerGame, position, teamid));
    }

    @Override
    public Player update(Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        Player playerid = playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        Team teamid = teamService.findById(team);
        playerid.setName(name);
        playerid.setBio(bio);
        playerid.setPointsPerGame(pointsPerGame);
        playerid.setPosition(position);
        playerid.setTeam(teamid);
        return playerRepository.save(playerid);
    }

    @Override
    public Player delete(Long id) {
        Player playerid = playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        playerRepository.delete(playerid);
        return playerid;
    }

    @Override
    public Player vote(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        // Use AtomicInteger to handle concurrent updates
        int votes = player.getVotes();
        votes++;
        player.setVotes(votes);
        // Save changes to the database
        playerRepository.save(player);
        return player;
    }

    @Override
    public List<Player> listPlayersWithPointsLessThanAndPosition(Double pointsPerGame, PlayerPosition position) {
        if (pointsPerGame != null && position != null) {
            return playerRepository.findPlayersByPointsPerGameLessThanAndPosition(pointsPerGame, position);
        }
        if (pointsPerGame != null) {
            return playerRepository.findPlayersByPointsPerGameLessThan(pointsPerGame);
        }
        if (position != null) {
            return playerRepository.findPlayersByPosition(position);
        }
        return playerRepository.findAll();
    }
}