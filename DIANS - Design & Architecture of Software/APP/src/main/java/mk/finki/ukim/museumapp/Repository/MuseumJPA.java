package mk.finki.ukim.museumapp.Repository;

import mk.finki.ukim.museumapp.PipeAndFilter.model.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuseumJPA extends JpaRepository<Museum, Integer> {

    List<Museum> findAllBy();

    List<Museum> findMuseumsByNameContainingIgnoreCase(String search);

    List<Museum> findMuseumsByOpeningHoursIsNot(String Unknown);

    List<Museum> findMuseumsByFeeNotContaining(String No);

    List<Museum> findMuseumsByInternetAccessContaining(String Yes);

    List<Museum> findMuseumsByStreetContains(String Street);

    void deleteById(int id);

    Museum findMuseumById(int id);
}
