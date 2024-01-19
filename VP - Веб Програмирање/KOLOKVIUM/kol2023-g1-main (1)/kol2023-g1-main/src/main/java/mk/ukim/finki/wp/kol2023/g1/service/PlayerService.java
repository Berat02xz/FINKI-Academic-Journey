package mk.ukim.finki.wp.kol2023.g1.service;

import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidPlayerIdException;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidTeamIdException;

import java.util.List;

public interface PlayerService {

    /**
     * @return List of all players in the database
     */
    List<Player> listAllPlayers();

    /**
     * returns the player with the given id
     *
     * @param id The id of the player that we want to obtain
     * @return
     * @throws InvalidPlayerIdException when there is no player with the given id
     */
    Player findById(Long id);

    /**
     * This method is used to create a new player, and save it in the database.
     *
     * @param name
     * @param bio
     * @param pointsPerGame
     * @param position
     * @param team
     * @return The player that is created. The id should be generated when the player is created.
     * @throws InvalidTeamIdException when there is no team with the given id
     */
    Player create(String name, String bio, Double pointsPerGame, PlayerPosition position, Long team);

    /**
     * This method is used to update a player, and save it in the database.
     *
     * @param id The id of the player that is being edited
     * @param name
     * @param bio
     * @param pointsPerGame
     * @param position
     * @param team
     * @return The player that is updated.
     * @throws InvalidPlayerIdException when there is no player with the given id
     * @throws InvalidTeamIdException when there is no team with the given id
     */
    Player update(Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team);

    /**
     * Method that should delete a player. If the id is invalid, it should throw InvalidPlayerIdException.
     *
     * @param id
     * @return The player that is deleted.
     * @throws InvalidPlayerIdException when there is no player with the given id
     */
    Player delete(Long id);

    /**
     * Method that should vote for a player. If the id is invalid, it should throw InvalidPlayerIdException.
     *
     * @param id
     * @return The player that is voted for.
     * @throws InvalidPlayerIdException when there is no player with the given id
     */
    Player vote(Long id);

    /**
     * The implementation of this method should use repository implementation for the filtering.
     *
     * @param pointsPerGame   Double that is used to filter the players that have less pointsPerGame than this value.
     *                        This param can be null, and is not used for filtering in this case.
     * @param position        Used for filtering the players that play in that position.
     *                        This param can be null, and is not used for filtering in this case.
     * @return The players that meet the filtering criteria
     */
    List<Player> listPlayersWithPointsLessThanAndPosition(Double pointsPerGame, PlayerPosition position);
}
