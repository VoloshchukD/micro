package by.valashchuk.microcollector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CollectorScheduler {

    @Value("${micro.recipient.message.url}")
    private String microRecipientUrl;

    private static final Logger logger = LoggerFactory.getLogger(CollectorScheduler.class);
    private final RestTemplate restTemplate;

    public CollectorScheduler() {
        this.restTemplate = new RestTemplate();
    }

    @Autowired
    private SavedMessageRepository savedMessageRepository;

    @Scheduled(fixedRate = 15000)
    public void callMicroRecipientAndGetMessages() {
        logger.info("Calling micro-recipient to get messages");
        String response = restTemplate.getForObject(microRecipientUrl, String.class);
        if (response != null && !response.isEmpty() && !response.contains("[]")) {
            logger.info("Received messages from micro-recipient: " + response);
            SavedMessage messageToSave = new SavedMessage();
            messageToSave.setContent(response);
            SavedMessage responseSavedMessage = savedMessageRepository.save(messageToSave);
            logger.info("Messages saved: " + responseSavedMessage.toString());
        }
    }

}
