package pvr.venky.banking.service_impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pvr.venky.banking.dto.AccountDto;
import pvr.venky.banking.entity.Account;
import pvr.venky.banking.map.AccountMapper;
import pvr.venky.banking.repository.AcoountRepository;
import pvr.venky.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AcoountRepository accountReposotory;
	
	

	public AccountServiceImpl(AcoountRepository accountReposotory) {
		this.accountReposotory = accountReposotory;
	}



	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account= AccountMapper.mapToAccount(accountDto);
		Account saveAccount=accountReposotory.save(account);
		
		return AccountMapper.mapToAccountDto(saveAccount);
	}



	@Override
	public AccountDto getAccountById(Long id) {
		Account account= accountReposotory
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account Not Exist"));
		return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountReposotory
				.findById(id)
				.orElseThrow(()->new RuntimeException("Acoount is not exist"));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account saveAccount=accountReposotory.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}



	@Override
	public AccountDto withdrawl(Long id, double amount) {
		Account account=accountReposotory
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account Not Exitst"));
		if(account.getBalance()<amount) {
			new RuntimeException("Insufficent Funds");
		}
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account saveAccount =accountReposotory.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}



	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountReposotory.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
		 
	}



	@Override
	public void deleteAccount(long id) {
		Account account=accountReposotory
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account not find"));
		accountReposotory.deleteById(id);
	}

}
