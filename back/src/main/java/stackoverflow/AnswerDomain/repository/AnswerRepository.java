package stackoverflow.AnswerDomain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stackoverflow.AnswerDomain.entity.Answer;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "SELECT c FROM Answer c WHERE c.answerId = :answerId")
    Optional<Answer> findByAnswer(Long answerId);


}
