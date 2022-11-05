package stackoverflow.domain.question.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stackoverflow.domain.question.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @EntityGraph(attributePaths = {"account"})
    @Query("select question from Question question where question.id = :questionId")
    Optional<Question> findByIdWithAll(@Param("questionId") Long questionId);

    @EntityGraph(attributePaths = {"account"})
    @Query("select question from Question question where question.title like %:title%")
    Page<Question> searchByTitleWithAll(@Param("title") String title, Pageable pageable);

    @EntityGraph(attributePaths = {"account"})
    @Query("select question from Question question " +
            "join question.account account where account.id = :accountId")
    Page<Question> findByAccountWithAll(@Param("accountId") Long accountId, Pageable pageable);

    @EntityGraph(attributePaths = {"account"})
    @Query("select question from Question question " +
            "where question not in (select distinct question from Question question " +
            "left join question.answers answer where answer.selected = true) " +
            "and question.title like %:title%")
    Page<Question> findByUnAnsweredWithAll(@Param("title") String title, Pageable pageable);

    @EntityGraph(attributePaths = {"account"})
    @Query("select question from Question question where question.title like %:title% order by question.createdAt desc")
    List<Question> findWithAllOrderByCreatedAt(@Param("title") String title);
}
