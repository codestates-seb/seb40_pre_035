package stackoverflow.domain.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stackoverflow.domain.answer.entity.AnswerVote;

import java.util.Optional;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {

    @Query("select vote from AnswerVote vote " +
            "join vote.account account " +
            "join vote.answer answer " +
            "where account.id = :accountId and answer.id = :answerId")
    Optional<AnswerVote> findByAnswerAndAccount(@Param("accountId") Long accountId,
                                                    @Param("answerId") Long answerId);
}
