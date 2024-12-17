package com.tekarch.AccountServiceMS.Services;

import com.tekarch.AccountServiceMS.Model.Account;

import java.util.List;

public interface AccountService {

        /**
         * Create a new account.
         *
         * @param account The account to be created.
         * @return The created account.
         */
        Account createAccount(Account account);

        /**
         * Update an existing account.
         *
         * @param accountId      The ID of the account to be updated.
         * @param updatedAccount The updated account details.
         * @return The updated account.
         */
        Account updateAccount(Long accountId, Account updatedAccount);

        /**
         * Retrieve all accounts.
         *
         * @return A list of all accounts.
         */
        List<Account> getAllAccounts();

        /**
         * Retrieve accounts by user ID.
         *
         * @param userId The ID of the user whose accounts are to be retrieved.
         * @return A list of accounts belonging to the specified user.
         */
        List<Account> getAccountsByUserId(Integer userId);

        /**
         * Retrieve a single account by its ID.
         *
         * @param accountId The ID of the account to retrieve.
         * @return The account with the specified ID.
         */
        Account getAccountById(Long accountId);

        /**
         * Delete an account by its ID.
         *
         * @param accountId The ID of the account to delete.
         */
        void deleteAccount(Long accountId);

        /**
         * Retrieve balances for multiple accounts of a user.
         *
         * @param userId The ID of the user whose account balances are to be retrieved.
         * @return A list of accounts with their balances for the specified user.
         */
        List<Account> getBalancesByUserId(Integer userId);

        /**
         * Validate the existence of a user in the User Service.
         *
         * @param userId The ID of the user to validate.
         * @return True if the user exists, otherwise throws an exception.
         */
        boolean validateUserId(Integer userId);
        List<Account> getAccountsByType(String accountType);
        void deleteAccountsByType(String accountType);

}