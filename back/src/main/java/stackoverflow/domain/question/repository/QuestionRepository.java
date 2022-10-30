package stackoverflow.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stackoverflow.domain.question.entity.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select question from Question question " +
            "join fetch question.account account where question.id = :questionId")
    Optional<Question> findByIdWithAccount(@Param("questionId") Long questionId);

    @Query("select distinct question from Question question " +
            "join fetch question.account account " +
            "left join question.answers " +
            "left join question.questionVotes where question.id = :questionId")
    Optional<Question> findByIdWithAll(@Param("questionId") Long questionId);
}
