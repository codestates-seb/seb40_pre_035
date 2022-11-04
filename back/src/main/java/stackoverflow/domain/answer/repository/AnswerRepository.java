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

    // 모든 Answers 조회
    @EntityGraph(attributePaths = {"question", "account"})
    @Query(value = "select answer from Answer answer Order by answer.createdAt desc")
    Page<Answer> findByAnswerWithAll(Pageable pageable);

    // 주어진 Question Id에 해당하는 Answers 조회
    @EntityGraph(attributePaths = {"question", "account"})
    @Query(value = "select answer from Answer answer where answer.question.id = :questionId order by answer.createdAt desc")
    Page<Answer> findByQuestionWithAll(@Param("questionId") Long questionId, Pageable pageable);

    // 주어진 Account Id에 해당하는 Answers 조회
    @EntityGraph(attributePaths = {"question", "account"})
    @Query(value = "select answer from Answer answer where answer.account.id = :accountId order by answer.createdAt desc")
    Page<Answer> findByAccountWithAll(@Param("accountId") Long accountId, Pageable pageable);

    @EntityGraph(attributePaths = {"question", "account"})
    @Query("select answer from Answer answer where answer.id = :answerId")
    Optional<Answer> findByAnswer(@Param("answerId") Long answerId);

}
