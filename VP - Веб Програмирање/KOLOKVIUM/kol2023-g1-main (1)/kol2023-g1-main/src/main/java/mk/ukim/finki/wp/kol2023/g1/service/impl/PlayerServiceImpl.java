package mk.ukim.finki.wp.kol2023.g1.service.impl;

import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import mk.ukim.finki.wp.kol2023.g1.model.Team;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidPlayerIdException;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidTeamIdException;
import mk.ukim.finki.wp.kol2023.g1.repository.PlayerRepository;
import mk.ukim.finki.wp.kol2023.g1.repository.TeamRepository;
import mk.ukim.finki.wp.kol2023.g1.service.PlayerService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
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
        Team team1 = teamRepository.findById(team).orElseThrow(InvalidTeamIdException::new);
        return playerRepository.save(new Player(name,bio,pointsPerGame,position,team1));
    }

    @Override
    public Player update(Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        Player player1 = playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        player1.setName(name);
        player1.setBio(bio);
        player1.setPosition(position);
        player1.setPointsPerGame(pointsPerGame);
        Team team1 = teamRepository.findById(team).orElseThrow(InvalidTeamIdException::new);
        player1.setTeam(team1);
        return player1;
    }

    @Override
    public Player delete(Long id) {
        Player player1 = playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        playerRepository.delete(player1);
        return player1;
    }

    @Override
    public Player vote(Long id) {
        Player player1 = playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        int votes = player1.getVotes()+1;
        player1.setVotes(votes);
        return player1;
    }

    @Override
    public List<Player> listPlayersWithPointsLessThanAndPosition(Double pointsPerGame, PlayerPosition position) {

        if(pointsPerGame==null && position==null){
            return playerRepository.findAll();
        }
        else if(pointsPerGame==null){
            return playerRepository.findPlayersByPosition(position);
        }
        else if(position==null){
            return playerRepository.findPlayersByPointsPerGameLessThan(pointsPerGame);
        }
        else{
            return playerRepository.findPlayersByPointsPerGameLessThanAndPosition(pointsPerGame,position);
        }
    }
}
