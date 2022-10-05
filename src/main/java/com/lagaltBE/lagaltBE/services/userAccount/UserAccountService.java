package com.lagaltBE.lagaltBE.services.userAccount;

import com.lagaltBE.lagaltBE.models.UserAccount;
import com.lagaltBE.lagaltBE.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface UserAccountService extends CrudService<UserAccount, Integer> {
}
