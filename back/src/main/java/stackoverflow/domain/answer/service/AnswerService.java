package stackoverflow.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Transactional
    public Answer updateAnswer(Answer answer) {
        Answer verifiedAnswer = findVerifiedAnswer(answer.getId());
        Optional.ofNullable(answer.getContent()).ifPresent(content -> verifiedAnswer.setContent(content));

        return verifiedAnswer;
    }


    public Answer findAnswer(Long answerId) {
        Answer verifiedAnswer = findVerifiedAnswer(answerId);

        return verifiedAnswer;
    }


    public Page<Answer> findAnswers(Pageable pageable) {

        return answerRepository.findAllByOrderByIdDesc(pageable);
    }


    public void deleteAnswer(Long id) {
        Answer verifiedAnswer = findVerifiedAnswer(id);

        answerRepository.delete(verifiedAnswer);
    }


    public Page<Answer> findAccountAnswers(Long accountId, Pageable pageable) {

        // mock data
        List<Answer> list = new ArrayList<>();

        Account account = new Account();  // 나중에 AccountResDto 완성되면 바꾸기
        account.setId(accountId);
        account.setEmail("mock@gmail.com");
        account.setProfile("profile");
        account.setNickname("nick");

        Question question = new Question();
        question.setAccount(account);

        for(int i =1 ; i <=10 ; i++) {
            Answer answer = new Answer();
            answer.setId(0L + i);
            answer.setContent("con"+i);
            answer.setTotalVote(10);
            answer.setQuestion(question);
            answer.setAccount(account);
            answer.setCreatedAt(LocalDateTime.now());
            answer.setModifiedAt(LocalDateTime.now());
            list.add(answer);
        }
        Page<Answer> page = new PageImpl<>(list, pageable, 10);

        return page;
    }


    public Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer verifiedAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return verifiedAnswer;
    }
}

