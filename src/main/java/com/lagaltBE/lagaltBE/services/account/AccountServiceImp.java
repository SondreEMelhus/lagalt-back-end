package com.lagaltBE.lagaltBE.services.account;

import com.lagaltBE.lagaltBE.exceptions.EntityNotFoundException;
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
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
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
    }

    @Override
    public void delete(Account entity) {
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByName(username);
    }
}
