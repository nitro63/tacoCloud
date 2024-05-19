package ekow.tacocloud.application;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//
@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(classes = TacoCloudApplication.class)
class TacoCloudApplicationTests {

    @Test
    void contextLoads() {
    }

}
