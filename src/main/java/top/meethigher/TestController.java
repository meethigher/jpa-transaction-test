package top.meethigher;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {


    private final TestService testService;

    @GetMapping("/testRollback1")
    public String testRollback() throws Exception {
        testService.rollback1();
        return "success";
    }

    @GetMapping("/testRollback2")
    public String testRollback2() throws Exception {
        testService.rollback2();
        return "success";
    }
}
