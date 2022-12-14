package stackoverflow.domain.answer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stackoverflow.domain.answer.entity.Answer;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @EntityGraph(attributePaths = {"question", "account"})
    Page<Answer> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @EntityGraph(attributePaths = {"question", "account"})
    @Query(value = "select answer from Answer answer where answer.question.id = :questionId order by answer.createdAt desc")
    Page<Answer> findByQuestionWithAll(@Param("questionId") Long questionId, Pageable pageable);

    @EntityGraph(attributePaths = {"question", "account"})
    @Query(value = "select answer from Answer answer where answer.id = :answerId")
    Optional<Answer> findByIdWithAll(@Param("answerId") Long answerId);

    @EntityGraph(attributePaths = {"account"})
    @Query("select answer from Answer answer where answer.id = :answerId")
    Optional<Answer> findByIdWithAccount(@Param("answerId") Long answerId);

    @EntityGraph(attributePaths = {"question", "account"})
    @Query(value = "select answer from Answer answer where answer.account.id = :accountId order by answer.createdAt desc")
    Page<Answer> findByAccountWithAll(@Param("accountId") Long accountId, Pageable pageable);

}
