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
    public void addAnswer(Answer answer) {

        checkAccount(answer.getAccount().getId());
        checkQuestion(answer.getQuestion().getId());

        answerRepository.save(answer);

    }


    @Transactional
    public void modifyAnswer(Answer answer) {

        Answer verifiedAnswer = checkQuestionAnswer(answer.getId());
        verifyAccount(answer.getAccount().getId(), verifiedAnswer.getAccount().getId());

        answer.setModifiedAt(LocalDateTime.now());
        Optional.ofNullable(answer.getContent()).ifPresent(content -> verifiedAnswer.setContent(content));  // patchAnswer로 변경될 사항 추가하는 부

    }


    @Transactional
    public void removeAnswer(Long loginAccountId, Long answerId) {

        Answer verifiedAnswer = checkQuestionAnswer(answerId);
        verifyAccount(loginAccountId, verifiedAnswer.getAccount().getId());

        answerVoteRepository.deleteAll(verifiedAnswer.getAnswerVotes());
        answerRepository.delete(verifiedAnswer);

    }


    public Answer findAnswer(Long answerId) {
        return checkQuestionAnswer(answerId);
    }


    public Page<Answer> findAnswers(Pageable pageable) {
        return answerRepository.findAllByOrderByIdDesc(pageable);
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

        verifyAccount(loginAccountId, question.getAccount().getId());

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

        checkAccount(answerVote.getAccount().getId());
        checkQuestionAnswer(answerVote.getAnswer().getId());

    }


    private static void isSelectedAnswer(List<Answer> answers) {

        for (Answer questionAnswer : answers) {
            if (questionAnswer.isSelected()) {
                throw new BusinessLogicException(ExceptionCode.DUPLICATED_SELECT);
            }
        }

    }


    private Answer verifiedAnswerWithAll(Long answerId) {

        Answer answer = checkQuestionAnswer(answerId);
        checkQuestion(answer.getQuestion().getId());
        checkAccount(answer.getAccount().getId());

        return answer;

    }


    private Answer checkQuestionAnswer(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ANSWER));  // 확인
    }


    private Account checkAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));  // 확인
    }


    private Question checkQuestion(Long questionId) {
        return questionRepository.findByIdWithAll(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));  // 확인
    }


    private void verifyAccount (Long loginAccountId, Long accountId) {
        if (!loginAccountId.equals(accountId)) {
            throw new BusinessLogicException(ExceptionCode.NON_ACCESS_MODIFY);
        }
    }
}