package stackoverflow.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.domain.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
