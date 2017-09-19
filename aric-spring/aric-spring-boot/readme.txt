
 使用spring boot 可以使用两种方式执行
 （1）pom文件采用继承spring boot 的引用在pom文件里加入以下代码：

  <!--此处的父引用是为spring-boot所引用的-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.3.5.RELEASE</version>
    </parent>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

 （2）直接引用以下依赖
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>${spring-boot.version}</version>
     </dependency>


启动类实例：
     /**
      * 程序入口类
      * 注解说明：
      * EnableAutoConfiguration
      * 这个注解告诉Spring Boot根据添加的jar依赖猜测你想如何配置Spring。即自动配置
      * ComponentScan
      * 组件自动扫描
      *Configuration
      * 配置控制，可以认为这是一个配置文件
      */
     @Configuration
     @EnableAutoConfiguration
     @ComponentScan(basePackages = {"com.aric"})
     public class ApplicationMain {
         public static void main(String[] args) {
             SpringApplication.run(ApplicationMain.class, args);
         }
     }