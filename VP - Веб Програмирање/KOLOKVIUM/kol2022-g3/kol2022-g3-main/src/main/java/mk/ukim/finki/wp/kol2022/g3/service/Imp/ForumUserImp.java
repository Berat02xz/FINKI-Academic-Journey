package mk.ukim.finki.wp.kol2022.g3.service.Imp;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUserType;
import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidForumUserIdException;
import mk.ukim.finki.wp.kol2022.g3.repository.ForumUserRepository;
import mk.ukim.finki.wp.kol2022.g3.repository.InterestRepository;
import mk.ukim.finki.wp.kol2022.g3.service.ForumUserService;
import mk.ukim.finki.wp.kol2022.g3.service.InterestService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForumUserImp implements ForumUserService {

    private final InterestRepository interestRepository;
    private final PasswordEncoder PasswordEncoder;
    private final ForumUserRepository forumUserRepository;

    public ForumUserImp(InterestRepository interestRepository, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder, ForumUserRepository forumUserRepository) {
        this.interestRepository = interestRepository;
        PasswordEncoder = passwordEncoder;
        this.forumUserRepository = forumUserRepository;
    }

    @Override
    public List<ForumUser> listAll() {
        return forumUserRepository.findAll();
    }

    @Override
    public ForumUser findById(Long id) {
        return forumUserRepository.findById(id).orElseThrow(InvalidForumUserIdException::new);
    }

    @Override
    public ForumUser create(String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday) {

        List<Interest> interests = new ArrayList<>();

        for(int i=0;i<interestId.size();i++){
            Long inte = interestId.get(i);
            Interest interest = this.interestRepository.findById(inte).orElseThrow(InvalidForumUserIdException::new);
            interests.add(interest);
        }

        return forumUserRepository.save(new ForumUser(name, email, PasswordEncoder.encode(password), type, interests, birthday));
    }

    @Override
    public ForumUser update(Long id, String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday) {
        ForumUser forumUser = forumUserRepository.findById(id).orElseThrow(InvalidForumUserIdException::new);
        forumUser.setName(name);
        forumUser.setEmail(email);
        forumUser.setPassword(PasswordEncoder.encode(password));
        forumUser.setType(type);
        forumUser.setBirthday(birthday);


        List<Interest> interests = new ArrayList<>();
        for(int i=0;i<interestId.size();i++){
            Interest interest = interestRepository.findById(interestId.get(i)).orElseThrow(InvalidForumUserIdException::new);
            interests.add(interest);
        }

        forumUser.setInterests(interests);

        return forumUserRepository.save(forumUser);
    }

    @Override
    public ForumUser delete(Long id) {
        ForumUser forumUser = forumUserRepository.findById(id).orElseThrow(InvalidForumUserIdException::new);
        forumUserRepository.delete(forumUser);
        return forumUser;
    }

    @Override
    public List<ForumUser> filter(Long interestId, Integer age) {

        if(interestId!=null && age!=null){
            Interest interest = interestRepository.findById(interestId).orElseThrow(InvalidForumUserIdException::new);
            return forumUserRepository.findAllByInterestsContainingAndBirthdayBefore(interest, LocalDate.now().minusYears(age));
        }
        else if(interestId!=null){
            Interest interest = interestRepository.findById(interestId).orElseThrow(InvalidForumUserIdException::new);
            return forumUserRepository.findAllByInterestsContaining(interest);
        }
        else if(age!=null){
            return forumUserRepository.findAllByBirthdayBefore(LocalDate.now().minusYears(age));
        }
        else{
            return forumUserRepository.findAll();
        }

    }
}
