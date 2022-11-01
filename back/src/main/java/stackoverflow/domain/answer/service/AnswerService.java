package stackoverflow.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    private final AccountRepository accountRepository;

    @Transactional
    public Answer createAnswer(Answer answer) {
        Account verifiedAccount = accountRepository.findById(answer.getAccount().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));
        answer.setAccount(verifiedAccount);
        answer.setCreatedAt(LocalDateTime.now());
        answer.setModifiedAt(LocalDateTime.now());

        return answerRepository.save(answer);
    }

    @Transactional
    public Answer updateAnswer(Answer answer) {
        Answer verifiedAnswer = answerRepository.findByIdWithQuestion(answer.getId())   // 해당 Answer가 레포에 있는지 확인 + questionId
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));  // conflict로 pr 이후 수정하기 NOT_FOUND_ANSWER

        answer.setModifiedAt(LocalDateTime.now());  // answer의
        Optional.ofNullable(answer.getContent()).ifPresent(content -> verifiedAnswer.setContent(content));         // patchAnswer로 변경될 사항 추가하는 부분
        Optional.ofNullable(answer.getTotalVote()).ifPresent(totalVote -> verifiedAnswer.setTotalVote(totalVote));

        return verifiedAnswer;
    }

    @Transactional
    public Answer findAnswer(Long answerId) {
        Answer verifiedAnswer = findVerifiedAnswer(answerId);

        return verifiedAnswer;
    }

    @Transactional(readOnly = true)
    public Page<Answer> findAnswers(Pageable pageable) {
        Page<Answer> page = answerRepository.findAllByOrderByIdDesc(pageable);

        return page;
    }


    public void deleteAnswer(Long id) {
        Answer verifiedAnswer = findVerifiedAnswer(id);
        answerRepository.delete(verifiedAnswer);
    }

    @Transactional(readOnly = true)
    public Page<Answer> findAccountAnswers(Long accountId, Pageable pageable) {
        Page<Answer> page = answerRepository.findAllByOrderByIdDesc(pageable);
        List<Answer> list = page.getContent().stream()
                .filter(a -> a.getAccount().getId().equals(accountId))
                .collect(Collectors.toList());
        Page<Answer> filteredPage = new PageImpl<>(list, pageable, list.size());

        return filteredPage;
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer verifiedAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        return verifiedAnswer;
    }
}