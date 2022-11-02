package stackoverflow.domain.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.domain.answer.entity.AnswerVote;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
}
