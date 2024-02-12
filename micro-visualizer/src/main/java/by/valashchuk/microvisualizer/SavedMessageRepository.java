package by.valashchuk.microvisualizer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedMessageRepository extends JpaRepository<SavedMessage, Long> {
}
