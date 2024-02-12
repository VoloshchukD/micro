package by.valashchuk.microvisualizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SavedMessageService {

    @Autowired
    private SavedMessageRepository repository;


    public List<SavedMessage> getAllSavedMessages() {
        return repository.findAll();
    }
}
