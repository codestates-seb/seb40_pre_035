package stackoverflow.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stackoverflow.domain.question.entity.QuestionVote;

import java.util.Optional;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {

    @Query("select vote from QuestionVote vote " +
            "join vote.account account " +
            "join vote.question question " +
            "where account.id = :accountId and question.id = :questionId")
    Optional<QuestionVote> findByQuestionAndAccount(@Param("accountId") Long accountId,
                                                    @Param("questionId") Long questionId);
}
