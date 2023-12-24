package mk.finki.ukim.museumapp.PipeAndFilter.Service;

import mk.finki.ukim.museumapp.PipeAndFilter.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUser(String username, String password);

    Boolean userExist(String username);

    User createUser(String username, String password, String email);
}
