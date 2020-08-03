package dynamicProxy.account;

/**
 * @Author 李振华
 * @Date 2020/7/20 18:43
 */
public class Account {

        private Integer id;
        private String name;
        private Float money;
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Float getMoney() {
            return money;
        }
        public void setMoney(Float money) {
            this.money = money;
        }
    }
