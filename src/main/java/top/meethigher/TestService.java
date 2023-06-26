package top.meethigher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final PlanDao planDao;

    private final PlanTimeDao planTimeDao;


    public void rollback1() throws Exception {
        //事务失效
        rollbackOperation();
    }

    @Transactional(rollbackFor = Exception.class)
    public void rollback2() throws Exception {
        //事务有效
        Plan plan = new Plan();
        plan.setPlanId(UniqueIdGenerator.generateUniqueId());
        plan.setPlanName("test");
        planDao.save(plan);
        PlanTime planTime = new PlanTime();
        planTime.setTimeId(UniqueIdGenerator.generateUniqueId());
        planTime.setStartTime("00:00");
        planTime.setEndTime("10:00");
        planTime.setPlanId(plan.getPlanId());
        planTimeDao.save(planTime);
        int i = 1 / 0;
    }

    /**
     * 在这种情况下，当在一个方法中调用另一个带有@Transactional注解的方法时，确实会导致事务注解失效的情况。这是因为Spring的事务代理机制是通过基于AOP的代理对象来实现的。当你在同一个类中的一个方法中调用另一个方法时，调用将绕过代理对象，导致事务注解无效。
     *
     * 要解决这个问题，可以考虑将rollbackOperation()方法移动到另一个类中，并通过依赖注入的方式来调用它。这样可以确保代理对象正确地拦截方法调用，并应用事务注解。
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void rollbackOperation() throws Exception {
        Plan plan = new Plan();
        plan.setPlanId(UniqueIdGenerator.generateUniqueId());
        plan.setPlanName("test");
        planDao.save(plan);
        PlanTime planTime = new PlanTime();
        planTime.setTimeId(UniqueIdGenerator.generateUniqueId());
        planTime.setStartTime("00:00");
        planTime.setEndTime("10:00");
        planTime.setPlanId(plan.getPlanId());
        planTimeDao.save(planTime);
        int i = 1 / 0;
    }
}
