package mk.ukim.finki.wp.kol2023.g1.config;

import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import mk.ukim.finki.wp.kol2023.g1.service.TeamService;
import mk.ukim.finki.wp.kol2023.g1.service.PlayerService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final TeamService teamService;

    private final PlayerService playerService;

    public DataInitializer(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    private PlayerPosition randomizePosition(int i) {
        if(i % 3 == 0) return PlayerPosition.G;
        else if(i % 3 == 1) return PlayerPosition.F;
        return PlayerPosition.C;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            this.teamService.create("Team: " + i);
        }

        for (int i = 1; i < 11; i++) {
            this.playerService.create("Player: " + i, "Bio: " + i , 20.9 * i, this.randomizePosition(i), this.teamService.listAll().get((i-1)%5).getId());
        }
    }
}
