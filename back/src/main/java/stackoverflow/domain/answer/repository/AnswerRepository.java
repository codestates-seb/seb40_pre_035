package stackoverflow.domain.answer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stackoverflow.domain.answer.entity.Answer;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Optional<Answer> findById(Long id);

    Page<Answer> findAllByOrderByIdDesc(Pageable pageable);

    @Query(value = "SELECT m FROM Answer m WHERE m.account.id = :accountId")
    Page<Answer> findAllByAccountId(Long accountId, Pageable pageable);
}
