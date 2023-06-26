在这种情况下，当在一个方法中调用另一个带有`@Transactional`注解的方法时，确实会导致事务注解失效的情况。这是因为Spring的事务代理机制是通过基于AOP的代理对象来实现的。当你在同一个类中的一个方法中调用另一个方法时，调用将绕过代理对象，导致事务注解无效。

要解决这个问题，可以考虑将`rollbackOperation()`方法移动到另一个类中，并通过依赖注入的方式来调用它。这样可以确保代理对象正确地拦截方法调用，并应用事务注解。

例如，你可以创建一个新的类，将`rollbackOperation()`方法放在该类中，并在需要调用该方法的地方通过依赖注入方式使用：

```
javaCopy code@Transactional(rollbackFor = Exception.class)
public class RollbackService {
    public void rollbackOperation() throws Exception {
        // 执行操作，可能抛出异常
        int i = 1 / 0;
    }
}
```

然后，在需要调用`rollbackOperation()`方法的地方，通过依赖注入`RollbackService`并调用相应的方法：

```
javaCopy codepublic class SomeService {
    private final RollbackService rollbackService;

    public SomeService(RollbackService rollbackService) {
        this.rollbackService = rollbackService;
    }

    public void rollback1() throws Exception {
        rollbackService.rollbackOperation();
    }
}
```

这样，`rollbackOperation()`方法将由代理对象处理，并且事务注解将起作用。

请确保在配置类上使用`@EnableTransactionManagement`注解来启用事务管理，并正确配置了事务管理器和扫描的包。

```
javaCopy code@Configuration
@EnableTransactionManagement
public class AppConfig {
    // 配置事务管理器和数据源等
}
```

通过将需要应用事务的方法移动到不同的类中并进行依赖注入，可以确保事务注解正确地应用于方法调用，从而使事务正常工作。


参考[JPA的事务注解@Transactional使用总结_jpa transactional_qq_duhai的博客-CSDN博客](https://blog.csdn.net/qq_16504067/article/details/117252765)
