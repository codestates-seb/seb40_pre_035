package stackoverflow.domain.answer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stackoverflow.domain.answer.entity.Answer;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Optional<Answer> findById(Long id);

    Page<Answer> findAllByOrderByIdDesc(Pageable pageable);

    @Query("select answer from Answer answer " +
            "join fetch answer.question question where answer.id = :answerId")
    Optional<Answer> findByIdWithQuestion(@Param("answerId") Long answerId);

    @Query("select answer from Answer answer " +
            "join fetch answer.account account where answer.id = :answerId")
    Optional<Answer> findByIdWithAccount(@Param("answerId") Long answerId);
}
