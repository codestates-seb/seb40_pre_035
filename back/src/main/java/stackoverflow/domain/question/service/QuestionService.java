package stackoverflow.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.domain.question.dto.QuestionsResDto;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.entity.QuestionVote;
import stackoverflow.domain.question.repository.QuestionRepository;
import stackoverflow.domain.question.repository.QuestionVoteRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AccountRepository accountRepository;
    private final AnswerRepository answerRepository;
    private final QuestionVoteRepository questionVoteRepository;

    @Transactional
    public Question addQuestion(Question question) {

        accountRepository.findById(question.getAccount().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));

        return questionRepository.save(question);
    }

    @Transactional
    public void modifyQuestion(Question question) {

        Long loginAccountId = question.getAccount().getId();
        accountRepository.findById(loginAccountId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));

        Question findQuestion = questionRepository.findByIdWithAll(question.getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));
        verifyAccess(findQuestion, loginAccountId);
        findQuestion.modify(question);
    }

    @Transactional
    public void removeQuestion(Long loginAccountId, Long questionId) {

        accountRepository.findById(loginAccountId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));

        Question findQuestion = questionRepository.findByIdWithAll(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));

        verifyAccess(findQuestion, loginAccountId);

        answerRepository.deleteAll(findQuestion.getAnswers());
        questionVoteRepository.deleteAll(findQuestion.getQuestionVotes());
        questionRepository.delete(findQuestion);
    }

    public Question findQuestion(Long questionId) {
        return questionRepository.findByIdWithAll(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));
    }

    public Page<Question> findQuestions(String keyword, Pageable pageable) {
        return questionRepository.searchByTitleWithAll(keyword, pageable);
    }

    public Page<Question> findAccountQuestions(Long accountId, Pageable pageable) {
        return questionRepository.findByAccountWithAll(accountId, pageable);
    }

    public Page<Question> findUnAnsweredQuestions(String keyword, Pageable pageable) {
        return questionRepository.findByUnAnsweredWithAll(keyword, pageable);
    }

    public Page<QuestionsResDto> findTotalVoteQuestions(String keyword, Pageable pageable) {

        List<Question> questions = questionRepository.findWithAllOrderByCreatedAt(keyword);
        List<QuestionsResDto> questionsResDtos = questions.stream()
                .map(QuestionsResDto::new)
                .sorted((q1, q2) -> q1.getTotalVote() > q2.getTotalVote() ? -1 : 1)
                .collect(Collectors.toList());

        int fromIndex = Math.min(pageable.getPageNumber() * pageable.getPageSize(), questions.size());
        int toIndex = Math.min(fromIndex + pageable.getPageSize(), questions.size());
        List<QuestionsResDto> questionsResDtoPage = questionsResDtos.subList(fromIndex, toIndex);

        return new PageImpl<>(questionsResDtoPage, pageable, questions.size());
    }

    @Transactional
    public String voteQuestion(QuestionVote questionVote) {

        verifyQuestionVoteField(questionVote);

        Long accountId = questionVote.getAccount().getId();
        Long questionId = questionVote.getQuestion().getId();
        Optional<QuestionVote> findOptionalQuestionVote =
                questionVoteRepository.findByQuestionAndAccount(accountId, questionId);

        if (findOptionalQuestionVote.isEmpty()) {
            questionVoteRepository.save(questionVote);
            return "success vote";
        }

        QuestionVote findQuestionVote = findOptionalQuestionVote.get();

        if (questionVote.getState().equals(findQuestionVote.getState())) {
            questionVoteRepository.delete(findQuestionVote);
            return "cancel vote";
        } else {
            throw new BusinessLogicException(ExceptionCode.ILLEGAL_VOTE);
        }

    }

    private void verifyQuestionVoteField(QuestionVote questionVote) {

        accountRepository.findById(questionVote.getAccount().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));

        questionRepository.findById(questionVote.getQuestion().getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_QUESTION));
    }

    private void verifyAccess(Question question, Long accountId) {
        if (!accountId.equals(question.getAccount().getId())) {
            throw new BusinessLogicException(ExceptionCode.NON_ACCESS_MODIFY);
        }
    }
}
