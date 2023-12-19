package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    //for mock testing
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account userRegistration(Account account){
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()){
            return null;
        }
        if (account.getPassword().length() < 4){
            return null;
        }
        if (accountDAO.checkIfUsernameExists(account.getUsername()) == true){
            return null; 
        }
        return accountDAO.userRegistration(account);
    }

    public Account loginAccount(Account account){
        return accountDAO.loginAccount(account); 
    }

}
