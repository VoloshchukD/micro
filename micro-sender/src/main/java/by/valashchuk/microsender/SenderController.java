package by.valashchuk.microsender;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {

    public static final String HIT_COUNTER_LABEL = "hit_counter";
    public static final String NUMBER_OF_HITS_DESCRIPTION = "Number of hits:";
    @Value("${rabbit.mq.queue.name}")
    private String queueName;

    private Counter hitCounter;

    private static final Logger logger = LoggerFactory.getLogger(SenderController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public SenderController(MeterRegistry meterRegistry) {
        hitCounter = Counter.builder(HIT_COUNTER_LABEL)
                .description(NUMBER_OF_HITS_DESCRIPTION)
                .register(meterRegistry);
    }

    @PostMapping("/notification")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest request) {
        hitCounter.increment();
        rabbitTemplate.convertAndSend(queueName, queueName, request.getMessage());
        logger.info("Notification sent: " + request.getMessage());
        return ResponseEntity.ok("Notification sent successfully");
    }
}