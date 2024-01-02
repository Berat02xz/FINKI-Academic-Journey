package mk.ukim.finki.wp.kol2022.g3.repository;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface ForumUserRepository extends JpaRepository<ForumUser,Long> {
    List<ForumUser> findAllByInterestsContaining(Interest interest);

    List<ForumUser> findAllByInterestsContainingAndBirthdayBefore(Interest interest, LocalDate localDate);

    List<ForumUser> findAllByBirthdayBefore(LocalDate localDate);
}
