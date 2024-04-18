package mk.finki.ukim.museumapp.Repository.Implementation;

import mk.finki.ukim.museumapp.PipeAndFilter.Service.UserService;
import mk.finki.ukim.museumapp.PipeAndFilter.model.User;
import mk.finki.ukim.museumapp.Repository.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImplementation implements UserService {

    private final UserJPA userJPA;

    @Autowired
    public UserImplementation(UserJPA userJPA) {
        this.userJPA = userJPA;
    }

    @Override
    public User getUser(String username, String password) {
        return userJPA.findUserByUsernameAndPassword(username,password);
    }

    @Override
    public Boolean userExist(String username) {
        User user = userJPA.findUserByUsername(username);
        if (user == null)
            return false;
        else {}
        return true;
    }

    @Override
    public User createUser(String username, String password, String email) {
        return userJPA.save(new User(username,password,email));
    }
}
