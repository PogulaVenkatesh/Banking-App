package pvr.venky.banking.map;

import pvr.venky.banking.dto.AccountDto;
import pvr.venky.banking.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(final AccountDto accountDto) {
		
		final Account account=new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
				);
		return account;
	}
	
	
	public static AccountDto mapToAccountDto(final Account account) {
		
		final AccountDto accountDto= new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return accountDto;
	}
	
}
