package stackoverflow.domain.answer.service;

import org.springframework.stereotype.Service;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer (Answer answer) {

        return answerRepository.save(answer);
    }

    public Answer updateAnswer (Answer answer) {

        return answerRepository.save(answer);
    }

    public Answer findAnswer (Long answerId) {

        return null;
    }

    public Answer findAnswers () {
//        return answerRepository.findAll();
        return null;
    }

    public void deleteAnswer (Long answerId) {
        Answer answer = findVerifiedAnswer(answerId);
        answerRepository.delete(answer);
    }

    public Answer findVerifiedAnswer (Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findByAnswer(answerId);
        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }
}

