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
import stackoverflow.domain.answer.entity.AnswerVote;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.domain.answer.repository.AnswerVoteRepository;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.repository.QuestionRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

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
    private final AnswerVoteRepository answerVoteRepository;

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
        Long loginAccountId = answer.getAccount().getId();

        if (!savedAccountId.equals(loginAccountId)) {
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


    @Transactional
    public void removeAnswer(Long loginAccountId, Long answerId) {
        Answer verifiedAnswer = findVerifiedAnswer(answerId);

        if (!loginAccountId.equals(verifiedAnswer.getAccount().getId())) {
            throw new BusinessLogicException(ExceptionCode.NON_ACCESS_MODIFY);
        }

        answerVoteRepository.deleteAll(verifiedAnswer.getAnswerVotes());
        answerRepository.delete(verifiedAnswer);
    }


    public Page<Answer> findAccountAnswers(Long accountId, Pageable pageable) {

        return answerRepository.findByAccountWithAll(accountId, pageable);
    }

    public Page<Answer> findQuestionAnswers(Long questionId, Pageable pageable) {
        Page<Answer> byQuestionWithAll = answerRepository.findByQuestionWithAll(questionId, pageable);
        return byQuestionWithAll;
    }

    @Transactional
    public void selectAnswer(Long loginAccountId, Long answerId) {

        Answer answer = verifiedAnswerWithAll(answerId);

        Question question = answer.getQuestion();

        if (!loginAccountId.equals(question.getAccount().getId())) {
            throw new BusinessLogicException(ExceptionCode.NON_ACCESS_MODIFY);
        }

        if (!answer.isSelected()) {
            List<Answer> answers = question.getAnswers();
            isSelectedAnswer(answers);
            answer.setSelected(true);
        } else {
            answer.setSelected(false);
        }
    }

    @Transactional
    public void voteAnswer(AnswerVote answerVote) {

        verifyAnswerVoteField(answerVote);

        Long accountId = answerVote.getAccount().getId();
        Long answerId = answerVote.getAnswer().getId();
        Optional<AnswerVote> findOptionalAnswerVote =
                answerVoteRepository.findByAnswerAndAccount(accountId, answerId);

        if (findOptionalAnswerVote.isEmpty()) {
            answerVoteRepository.save(answerVote);
            return;
        }

        AnswerVote findAnswerVote = findOptionalAnswerVote.get();

        if (answerVote.getState().equals(findAnswerVote.getState())) {
            answerVoteRepository.delete(findAnswerVote);
        } else {
            throw new BusinessLogicException(ExceptionCode.ILLEGAL_VOTE);
        }

    }

    private void verifyAnswerVoteField(AnswerVote answerVote) {
        accountRepository.findById(answerVote.getAccount().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));
        answerRepository.findById(answerVote.getAnswer().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));
    }

    private static void isSelectedAnswer(List<Answer> answers) {
        for (Answer questionAnswer : answers) {
            if (questionAnswer.isSelected()) {
                throw new BusinessLogicException(ExceptionCode.DUPLICATED_SELECT);
            }
        }
    }

    private Answer verifiedAnswerWithAll(Long answerId) {
        Answer answer = answerRepository.findByIdWithAll(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));

        questionRepository.findByIdWithAll(answer.getQuestion().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));

        accountRepository.findById(answer.getAccount().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));

        return answer;
    }

    public Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findByIdWithQuestion(answerId);
        Answer verifiedAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));

        return verifiedAnswer;
    }
}