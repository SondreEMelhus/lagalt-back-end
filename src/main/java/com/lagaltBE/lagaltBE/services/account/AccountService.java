package com.lagaltBE.lagaltBE.services.account;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface AccountService extends CrudService<Account, Integer> {
}
