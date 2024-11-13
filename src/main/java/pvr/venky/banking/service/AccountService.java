package pvr.venky.banking.service;

import java.util.List;

import pvr.venky.banking.dto.AccountDto;
//import pvr.venky.banking.entity.Account;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto); // To Save Account details
	AccountDto getAccountById(Long id);				// To get a person details by their id
	AccountDto deposit(Long id,double amount);		// Deposit to the existing account
	AccountDto withdrawl(Long id,double amount);	// withdrawal from existing account
	List<AccountDto> getAllAccounts();				// To get all accounts details
	void deleteAccount(long id);					// To delete account by using id
}
