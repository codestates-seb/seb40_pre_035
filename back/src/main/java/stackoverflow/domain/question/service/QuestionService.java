package stackoverflow.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.repository.QuestionRepository;
import stackoverflow.domain.question.repository.QuestionVoteRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AccountRepository accountRepository;
    private final AnswerRepository answerRepository;
    private final QuestionVoteRepository questionVoteRepository;

    @Transactional
    public void addQuestion(Question question) {

        Account account = accountRepository.findById(question.getAccount().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));

        question.setAccount(account);

        questionRepository.save(question);
    }

    @Transactional
    public void modifyQuestion(Question question) {

        Long loginAccountId = question.getAccount().getId();

        Question findQuestion = questionRepository.findByIdWithAccount(question.getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));

        verifyCreated(findQuestion, loginAccountId);
        findQuestion.modify(question);
    }

    public Question findQuestion(Long questionId) {
        return questionRepository.findByIdWithAll(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));
    }

    @Transactional
    public void removeQuestion(Question question) {

        Long loginAccountId = question.getAccount().getId();

        Question findQuestion = questionRepository.findByIdWithAll(question.getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));

        verifyCreated(findQuestion, loginAccountId);

        answerRepository.deleteAll(findQuestion.getAnswers());
        questionVoteRepository.deleteAll(findQuestion.getQuestionVotes());
        questionRepository.delete(question);
    }

    public Page<Question> findQuestions(String keyword, Pageable pageable) {
        return questionRepository.searchByTitleWithAll(keyword, pageable);
    }

    private void verifyCreated(Question question, Long accountId) {
        if (!accountId.equals(question.getAccount().getId())) {
            throw new BusinessLogicException(ExceptionCode.NON_ACCESS_MODIFY);
        }
    }
}
