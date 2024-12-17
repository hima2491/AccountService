package com.tekarch.AccountServiceMS.Services;

import com.tekarch.AccountServiceMS.Model.Account;
import com.tekarch.AccountServiceMS.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    // URL for User Service
    private final String USER_SERVICE_URL = "http://localhost:8081/users";

    /**
     * Helper method to validate the userId by calling User Service.
     */
    @Override
    public boolean validateUserId(Integer userId) {
        String url = USER_SERVICE_URL + "/" + userId; // User Service URL
        try {
            restTemplate.getForEntity(url, String.class);
            return true; // User exists
        } catch (Exception e) {
            throw new RuntimeException("User ID " + userId + " not found in User Service");
        }
    }

    /**
     * Create a new account after validating userId.
     */
    @Override
    public Account createAccount(Account account) {
        validateUserId(account.getUserId());
        return accountRepository.save(account);
    }

    /**
     * Update an existing account.
     */
    @Override
    public Account updateAccount(Long accountId, Account updatedAccount) {
        Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        if (updatedAccount.getAccountType() != null) existingAccount.setAccountType(updatedAccount.getAccountType());
        if (updatedAccount.getBalance() != null) existingAccount.setBalance(updatedAccount.getBalance());
        if (updatedAccount.getCurrency() != null) existingAccount.setCurrency(updatedAccount.getCurrency());

        return accountRepository.save(existingAccount);
    }

    /**
     * Retrieve all accounts.
     */
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Retrieve accounts for a specific user after validating the userId.
     */
    @Override
    public List<Account> getAccountsByUserId(Integer userId) {
        return accountRepository.findByUserId(userId);
    }


    /**
     * Retrieve an account by its ID.
     */
    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
    }

    /**
     * Delete an account by its ID.
     */
    @Override
    public void deleteAccount(Long accountId) {
        Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
        accountRepository.delete(existingAccount);
    }

    /**
     * Retrieve balances for multiple accounts of a specific user.
     */
    @Override
    public List<Account> getBalancesByUserId(Integer userId) {
        validateUserId(userId);
        return accountRepository.findByUserId(userId);
    }

    @Override
    public List<Account> getAccountsByType(String accountType) {
        return accountRepository.findByAccountType(accountType);
    }

    @Override
    public void deleteAccountsByType(String accountType) {
        List<Account> accountsToDelete = accountRepository.findByAccountType(accountType);
        if (accountsToDelete.isEmpty()) {
            throw new RuntimeException("No accounts found with accountType: " + accountType);
        }
        accountRepository.deleteAll(accountsToDelete);
    }
}
