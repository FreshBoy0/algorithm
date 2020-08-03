package dynamicProxy.account;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author 李振华
 * @Date 2020/7/20 18:45
 */
public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
    @Override
    public void saveAccount(Account account) {
        accountDao.save(account);
    }
    @Override
    public void updateAccount(Account account){
        accountDao.update(account);
    }
    @Override
    public void deleteAccount(Integer accountId){
        accountDao.delete(accountId);
    }
    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findById(accountId);
    }
    @Override
    public List<Account> findAllAccount(){
        return accountDao.findAll();
    }
}