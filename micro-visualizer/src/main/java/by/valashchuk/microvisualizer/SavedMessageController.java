package by.valashchuk.microvisualizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saved-messages")
public class SavedMessageController {

    @Autowired
    private SavedMessageService service;

    @GetMapping
    public List<SavedMessage> getAllSavedMessages() {
        return service.getAllSavedMessages();
    }
}
