package com.tekarch.AccountServiceMS.Controller;

import com.tekarch.AccountServiceMS.Model.Account;
import com.tekarch.AccountServiceMS.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Create a new account.
     */
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    /**
     * Update an existing account.
     */
    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(accountId, account);
        return ResponseEntity.ok(updatedAccount);
    }

    /**
     * Retrieve all accounts or filter accounts by userId.
     * If `userid` is passed as a query parameter, filter by userId.
     */
    @GetMapping
    public ResponseEntity<List<Account>> getAccounts(@RequestParam(value = "userid", required = false) Integer userid) {
        if (userid != null) {
            // Filter accounts for the specific userId
            List<Account> accounts = accountService.getAccountsByUserId(userid);
            return ResponseEntity.ok(accounts);
        } else {
            // Fetch all accounts if no userId is provided
            List<Account> accounts = accountService.getAllAccounts();
            return ResponseEntity.ok(accounts);
        }
    }



    /**
     * Retrieve account balance(s) for a specific user.
     */
    @GetMapping("/balance")
    public ResponseEntity<List<Account>> getBalancesByUserId(@RequestParam Integer userid) {
        List<Account> accountsWithBalances = accountService.getBalancesByUserId(userid);
        return ResponseEntity.ok(accountsWithBalances);
    }

    /**
     * Retrieve a single account by its ID.
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    /**
     * Delete an account by its ID.
     */
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type")
    public ResponseEntity<List<Account>> getAccountsByType(@RequestParam("accountType") String accountType) {
        List<Account> accounts = accountService.getAccountsByType(accountType);
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/type")
    public ResponseEntity<String> deleteAccountsByType(@RequestParam("accountType") String accountType) {
        accountService.deleteAccountsByType(accountType);
        return ResponseEntity.ok("Accounts with accountType '" + accountType + "' have been deleted successfully.");
    }



}

