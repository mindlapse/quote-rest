package ai.descent;

import ai.descent.api.QuoteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
@DirtiesContext
public class MainTest {

    @Autowired
    private QuoteSource publisher;

    @Test
    public void contextLoads() {
        assertNotNull(this.publisher.usdcad());
    }
}