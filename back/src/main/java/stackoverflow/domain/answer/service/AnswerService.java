package stackoverflow.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public void createAnswer(Answer answer) {
        Account verifiedAccount = accountRepository.findById(answer.getAccount().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));
        Question verifiedQuestion = questionRepository.findById(answer.getQuestion().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));
        answer.setAccount(verifiedAccount);
        answer.setQuestion(verifiedQuestion);

        answerRepository.save(answer);
    }

    @Transactional
    public void updateAnswer(Answer answer) {
        Answer verifiedAnswer = answerRepository.findByIdWithQuestion(answer.getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));

        Long savedAccountId = verifiedAnswer.getAccount().getId();
        Long loginedAccountId = answer.getAccount().getId();

        if (!savedAccountId.equals(loginedAccountId)) {
            throw new BusinessLogicException(ExceptionCode.NON_ACCESS_MODIFY);
        }
        else {
            answer.setModifiedAt(LocalDateTime.now());
            Optional.ofNullable(answer.getContent()).ifPresent(content -> verifiedAnswer.setContent(content));  // patchAnswer로 변경될 사항 추가하는 부
        }
    }


    public Answer findAnswer(Long answerId) {
        Answer verifiedAnswer = findVerifiedAnswer(answerId);

        return verifiedAnswer;
    }


    public Page<Answer> findAnswers(Pageable pageable) {
        Page<Answer> page = answerRepository.findAllByOrderByIdDesc(pageable);

        return page;
    }


    public void deleteAnswer(Long id) {
        Answer verifiedAnswer = findVerifiedAnswer(id);
        answerRepository.delete(verifiedAnswer);
    }


    public Page<Answer> findAccountAnswers(Long accountId, Pageable pageable) {
        Page<Answer> page = answerRepository.findAllByOrderByIdDesc(pageable);
        List<Answer> list = page.getContent().stream()
                .filter(a -> a.getAccount().getId().equals(accountId))
                .collect(Collectors.toList());

        return new PageImpl<>(list, pageable, list.size());
    }

    public Page<Answer> findQuestionAnswers(Long questionId, Pageable pageable) {
        Page<Answer> byQuestionWithAll = answerRepository.findByQuestionWithAll(questionId, pageable);
        return byQuestionWithAll;
    }

    public Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findByIdWithQuestion(answerId);
        Answer verifiedAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));

        return verifiedAnswer;
    }
}