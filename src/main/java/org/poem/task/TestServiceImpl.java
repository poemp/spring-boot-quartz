package org.poem.task;

import org.poem.api.test.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "testService")
public class TestServiceImpl  implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public void run() {
        logger.info("执行：TestServiceImpl -- run");
    }
}
