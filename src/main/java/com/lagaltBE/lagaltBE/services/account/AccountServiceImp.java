package com.lagaltBE.lagaltBE.services.account;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    @Override
    public Account findById(Integer id) {
        // TODO add or else throw UserAccountNotFoundException like this: or find another solution
        // return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return accountRepository.findById(id).get();
    }

    @Override
    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account add(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Account update(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void delete(Account entity) {
        accountRepository.delete(entity);
    }
}
