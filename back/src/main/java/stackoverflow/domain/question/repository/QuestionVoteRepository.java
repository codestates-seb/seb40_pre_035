package stackoverflow.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.domain.question.entity.QuestionVote;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
}
