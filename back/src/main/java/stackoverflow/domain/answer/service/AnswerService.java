package stackoverflow.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;


    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }


    public Answer updateAnswer(Answer answer) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answer.getAnswerId());
        Answer verifiedAnswer = optionalAnswer.orElseThrow(()-> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        Optional.ofNullable(answer.getAnswerId()).ifPresent(answerId -> verifiedAnswer.setAnswerId(answerId));
        Optional.ofNullable(answer.getTitle()).ifPresent(title -> verifiedAnswer.setTitle(title));
        Optional.ofNullable(answer.getContent()).ifPresent(content -> verifiedAnswer.setContent(content));

        return answerRepository.save(verifiedAnswer);
    }


    public Answer findAnswer(Long answerId) {
        Answer verifiedAnswer = findVerifiedAnswer(answerId);

        return verifiedAnswer;
    }


    public List<Answer> findAnswers() {
        List<Answer> answerList = answerRepository.findAll();

        return answerList;
    }


    public void deleteAnswer(Long answerId) {
        Answer verifiedAnswer = findVerifiedAnswer(answerId);

        answerRepository.delete(verifiedAnswer);
    }


    public Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer verifiedAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return verifiedAnswer;
    }
}

