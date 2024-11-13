package pvr.venky.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pvr.venky.banking.dto.AccountDto;
import pvr.venky.banking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping   //("/api/accounts")
	public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto){
		
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto=accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/diposit")
	public ResponseEntity<AccountDto> diposit(@PathVariable Long id, @RequestBody Map<String, Double> Request){
		
		double amount=Request.get("amount");
		AccountDto accountDto=accountService.deposit(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/withdrawal")
	public ResponseEntity<AccountDto> withdrawl(@PathVariable Long id,@RequestBody Map<String, Double> Request){
		double amount=Request.get("amount");
		AccountDto accountDto=accountService.withdrawl(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}

	@GetMapping  //("/allAccounts")
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts = accountService.getAllAccounts();
		
		return ResponseEntity.ok(accounts);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account "+ id +" is deleted sucussfully");
	}

}
