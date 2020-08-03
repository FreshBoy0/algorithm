package dynamicProxy.account;

import java.util.List;

/**
 * @Author 李振华
 * @Date 2020/7/20 18:37
 */
public interface IAccountService {

    /**
     * 保存账户
     * @param account
     */
    void saveAccount(Account account);
    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);
    /**
     * 删除账户
     * @param accountId
     */
    void deleteAccount(Integer accountId);
    /**
     * 根据 id 查询账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);
    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAllAccount();


}
