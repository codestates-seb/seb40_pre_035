package stackoverflow.AnswerDomain.service;

import org.springframework.stereotype.Service;
import stackoverflow.AnswerDomain.entity.Answer;
import stackoverflow.AnswerDomain.repository.AnswerRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.advice.ExceptionCode;

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
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));   // global Exception 구현해야함
        return findAnswer;
    }
}

