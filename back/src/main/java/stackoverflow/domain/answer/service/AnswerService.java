package stackoverflow.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;
    private final AnswerVoteRepository answerVoteRepository;

    private static void isSelectedAnswer(List<Answer> answers) {
        for (Answer questionAnswer : answers) {
            if (questionAnswer.isSelected()) {
                throw new BusinessLogicException(ExceptionCode.DUPLICATED_SELECT);
            }
        }
    }

    @Transactional
    public void addAnswer(Answer answer) {
        verifyAccount(answer.getAccount().getId());
        verifyQuestion(answer.getQuestion().getId());

        answerRepository.save(answer);
    }

    @Transactional
    public void modifyAnswer(Answer answer) {
        Answer findAnswer = answerRepository.findByIdWithAccount(answer.getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));

        Long savedAccountId = findAnswer.getAccount().getId();
        Long loginAccountId = answer.getAccount().getId();

        verifyAuthority(savedAccountId, loginAccountId);
        Optional.ofNullable(answer.getContent()).ifPresent(findAnswer::setContent);

    }

    public Answer findAnswer(Long answerId) {
        return answerRepository.findByIdWithAccount(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));
    }

    public Page<Answer> findAnswers(Pageable pageable) {
        return answerRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Transactional
    public void removeAnswer(Long loginAccountId, Long answerId) {
        Answer verifiedAnswer = verifyAnswer(answerId);

        verifyAuthority(loginAccountId, verifiedAnswer.getAccount().getId());
        answerVoteRepository.deleteAll(verifiedAnswer.getAnswerVotes());
        answerRepository.delete(verifiedAnswer);
    }

    public Page<Answer> findAccountAnswers(Long accountId, Pageable pageable) {

        return answerRepository.findByAccountWithAll(accountId, pageable);
    }

    public Page<Answer> findQuestionAnswers(Long questionId, Pageable pageable) {
        return answerRepository.findByQuestionWithAll(questionId, pageable);
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
    public String voteAnswer(AnswerVote answerVote) {

        verifyAnswerVoteField(answerVote);

        Long accountId = answerVote.getAccount().getId();
        Long answerId = answerVote.getAnswer().getId();
        Optional<AnswerVote> findOptionalAnswerVote =
                answerVoteRepository.findByAnswerAndAccount(accountId, answerId);

        if (findOptionalAnswerVote.isEmpty()) {
            answerVoteRepository.save(answerVote);
            return "success vote";
        }

        AnswerVote findAnswerVote = findOptionalAnswerVote.get();

        if (answerVote.getState().equals(findAnswerVote.getState())) {
            answerVoteRepository.delete(findAnswerVote);
            return "cancel vote";
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

    private Answer verifiedAnswerWithAll(Long answerId) {
        Answer answer = answerRepository.findByIdWithAll(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));

        questionRepository.findByIdWithAll(answer.getQuestion().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));

        accountRepository.findById(answer.getAccount().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));

        return answer;
    }


    private Question verifyQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));
    }

    private Account verifyAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));
    }

    private Answer verifyAnswer(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));
    }

    private void verifyAuthority(Long savedAccountId, Long loginAccountId) {
        if (!savedAccountId.equals(loginAccountId)) {
            throw new BusinessLogicException(ExceptionCode.NON_ACCESS_MODIFY);
        }
    }
}