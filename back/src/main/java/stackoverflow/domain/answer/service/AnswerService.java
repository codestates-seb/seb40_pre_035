package stackoverflow.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

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
        List<Answer> answerList = new ArrayList<>();  // 가급적이면 바꿀 수 있으면 바꾸기
        Answer verifiedAnswer = findVerifiedAnswer(accountId);
        answerList.add(verifiedAnswer);
        Page<Answer> answers = (Page<Answer>) answerList;

        return answers;
    }


    public Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer verifiedAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return verifiedAnswer;
    }
}

