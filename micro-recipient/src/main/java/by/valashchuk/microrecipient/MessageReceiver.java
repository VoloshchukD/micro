package by.valashchuk.microrecipient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageReceiver {

    @Value("${rabbit.mq.queue.name}")
    private String queueName;

    private List<String> messageList = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 3000)
    public void process() {
        logger.info("Processing messages from rabbitmq");
        Message message = rabbitTemplate.receive(queueName);
        if (message != null && message.getBody() != null
                && message.getBody().length > 0) {
            String messageBody = new String(message.getBody());
            logger.info("Receive message " + messageBody);
            messageList.add(messageBody);
        }
    }

    @GetMapping("/message")
    public ResponseEntity<String> getMessages() {
        String message = "";
        if (!messageList.isEmpty()) {
            message = messageList.remove(0);
        }
        return ResponseEntity.ok(message);
    }
}
