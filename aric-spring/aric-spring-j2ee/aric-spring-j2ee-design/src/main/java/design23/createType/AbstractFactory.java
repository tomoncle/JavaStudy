package design23.createType;

/**
 * 抽象工厂模式
 * 适应场景：
 * 1.当子类可能有很多，以后需要添加许多子类时
 * 2.不确定要实现某个子类时
 * 3.没有具体的对象概念
 * Created by tom.lee on 2016/3/24.
 */
public class AbstractFactory {

    public static void main(String[] args) {
        //创建语言工厂
        LanguageFactory languageFactory = null;
        //创建语言对象模型
        AbstractLanguage language = null;

        //实例化学生工厂
        languageFactory = new StudentLanguageFactoryImpl();
        //创建English语言
        language = languageFactory.createLanguage();
        System.err.println("学生说:" + language.getName());


        //实例化老师工厂
        languageFactory = new TeacherLanguageFactoryImpl();
        //创建Chinese语言
        language = languageFactory.createLanguage();
        System.err.println("老师说:" + language.getName());

    }
}


/**
 * 抽象语言
 */
abstract class AbstractLanguage {

    //语言名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 英语
 */
class English extends AbstractLanguage {
    public English() {
        this.setName("英语");
    }
}

class Chinese extends AbstractLanguage {
    public Chinese() {
        this.setName("汉语");
    }
}

/**
 * 语言工厂
 */
interface LanguageFactory {

    //创造语言
    AbstractLanguage createLanguage();

}

/**
 * 学生实现语言接口
 */
class StudentLanguageFactoryImpl implements LanguageFactory {

    @Override
    public AbstractLanguage createLanguage() {
        return new English();
    }
}

/**
 * 老师实现语言接口
 */
class TeacherLanguageFactoryImpl implements LanguageFactory {

    @Override
    public AbstractLanguage createLanguage() {
        return new Chinese();
    }
}




