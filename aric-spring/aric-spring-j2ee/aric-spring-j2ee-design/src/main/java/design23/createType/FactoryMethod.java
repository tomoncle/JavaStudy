package design23.createType;

/**
 * 工厂方法模式
 *
 * 使用场景：
 * 1.创建产品家族，相关产品集合一起使用
 * 2.提供一个产品类库，只想显示接口，而不暴露实现
 * 3.通过组合方式使用工厂时
 *
 * Created by tom.lee on 2016/3/24.
 */
public class FactoryMethod {

    public static void main(String[] args) {
        /**
         * 创建肯德基工厂实现（拿铁咖啡，香辣鸡腿堡）
         */
        KFCFactory kfcFactory = new KFCFactoryForChickenBurgerAndCoffee();
        Customer customer = new Customer(kfcFactory);

        Float money = customer.getChickenBurger(2);
        money = money + customer.getCoffeeDrinks(3);
        System.err.println("一共消费：" + money + "元。");

    }

}

/**
 * 抽象食物基类
 */
abstract class BaseFood {

    private String type;//食物类型

    private Float price;//食物单价

    private Integer number;//食物数量

    /**
     * 计算总价
     *
     * @return
     */
    public Float getTotalPrice() {
        return this.getPrice() * this.getNumber();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

/**
 * 抽象食物接口
 */
interface FoodService {
    /**
     * 打印食物信息
     */
    void printFoodMessage();
}

/**
 * 汉堡抽象类
 */
abstract class Hamburg extends BaseFood implements FoodService {
    @Override
    public void printFoodMessage() {
        System.out.println("汉堡：类型=" + getType() +
                ", 单价=" + getPrice() + "RMB" +
                ", 数量=" + getNumber() + "份" +
                "，总价=" + getTotalPrice() + "RMB");
    }

}

/**
 * 香辣鸡腿堡
 */
class ChickenBurger extends Hamburg {

    public ChickenBurger(int number) {
        super.setNumber(number);
        super.setPrice(13F);
        super.setType("香辣鸡腿堡");
    }
}


/**
 * 鳕鱼堡
 */
class FishBurger extends Hamburg {
    public FishBurger(int number) {
        super.setNumber(number);
        super.setPrice(15.0F);
        super.setType("鳕鱼堡");
    }
}


/**
 * 饮料抽象类
 */
abstract class Drinks extends BaseFood implements FoodService {

    @Override
    public void printFoodMessage() {
        System.out.println("饮料：类型=" + getType() +
                ", 单价=" + getPrice() + "RMB" +
                ", 数量=" + getNumber() + "份" +
                "，总价=" + getTotalPrice() + "RMB");
    }
}

/**
 * 咖啡
 */
class Coffee extends Drinks {
    public Coffee(int num) {
        super.setType("拿铁咖啡");
        super.setNumber(num);
        super.setPrice(11.5F);
    }
}

/**
 * 果汁
 */
class Juice extends Drinks {
    public Juice(int num) {
        super.setType("果汁");
        super.setNumber(num);
        super.setPrice(7.5F);
    }
}

/**
 * 肯德基工厂
 */
interface KFCFactory {

    /**
     * 生产汉堡
     *
     * @param num
     * @return
     */
    Hamburg createHamBurg(int num);

    /**
     * 生产饮料
     *
     * @param num
     * @return
     */
    Drinks createDrinks(int num);

}

/**
 * 肯德基工厂实现
 */
class KFCFactoryForChickenBurgerAndCoffee implements KFCFactory {

    /**
     * 生产香辣鸡腿堡
     *
     * @param num
     * @return
     */
    @Override
    public Hamburg createHamBurg(int num) {
        return new ChickenBurger(num);
    }

    /**
     * 生产拿铁咖啡
     *
     * @param num
     * @return
     */
    @Override
    public Drinks createDrinks(int num) {
        return new Coffee(num);
    }
}

/**
 * 客户
 */
class Customer {
    /**
     * 肯德基工厂--此处使用组合模式
     */
    private KFCFactory kfcFactory;

    public Customer(KFCFactory kfcFactory) {
        this.kfcFactory = kfcFactory;
    }


    /**
     * 获得鸡腿堡
     *
     * @param num
     * @return
     */
    public Float getChickenBurger(int num) {
        Hamburg chickenBurger = kfcFactory.createHamBurg(num);
        chickenBurger.printFoodMessage();
        return chickenBurger.getTotalPrice();
    }

    /**
     * 获得咖啡
     *
     * @param num
     * @return
     */
    public Float getCoffeeDrinks(int num) {
        Drinks coffee = kfcFactory.createDrinks(num);
        coffee.printFoodMessage();
        return coffee.getTotalPrice();
    }


}



