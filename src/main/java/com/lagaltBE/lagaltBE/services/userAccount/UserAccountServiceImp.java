package com.lagaltBE.lagaltBE.services.userAccount;

import com.lagaltBE.lagaltBE.models.UserAccount;
import com.lagaltBE.lagaltBE.repositories.UserAccountRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class UserAccountServiceImp implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImp(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
    
    @Override
    public UserAccount findById(Integer id) {
        // TODO add or else throw UserAccountNotFoundException like this: or find another solution
        // return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return userAccountRepository.findById(id).get();
    }

    @Override
    public Collection<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount add(UserAccount entity) {
        return userAccountRepository.save(entity);
    }

    @Override
    public UserAccount update(UserAccount entity) {
        return userAccountRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        userAccountRepository.deleteById(id);
    }

    @Override
    public void delete(UserAccount entity) {
        userAccountRepository.delete(entity);
    }
}
