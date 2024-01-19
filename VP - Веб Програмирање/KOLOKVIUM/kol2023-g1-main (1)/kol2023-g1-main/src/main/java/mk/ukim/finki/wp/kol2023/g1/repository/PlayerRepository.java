package mk.ukim.finki.wp.kol2023.g1.repository;

import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerRepository extends JpaRepository<Player,Long> {

    //listPlayersWithPointsLessThanAndPosition
    List<Player> findPlayersByPointsPerGameLessThanAndPosition(Double pointsPerGame, PlayerPosition position);

    List<Player>  findPlayersByPointsPerGameLessThan(Double pointsPerGame);

    List<Player> findPlayersByPosition(PlayerPosition position);
}
