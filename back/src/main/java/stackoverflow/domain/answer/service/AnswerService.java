package stackoverflow.domain.answer.service;

import org.springframework.stereotype.Service;
import stackoverflow.domain.answer.dto.AnswerResponseDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    // Mock Service
    public Answer createAnswer(Answer answer) {
        Answer createdAnswer = answer;
        return createdAnswer;
    }

    public Answer updateAnswer (Answer answer) {
        Answer updatedAnswer = answer;
        return updatedAnswer;
    }

    public Answer findAnswer(Long answerId) {
        Answer mockAnswer = new Answer();
        mockAnswer.setAnswerId(answerId);
        mockAnswer.setAnswerId(answerId);
        mockAnswer.setTitle("mockTitle");
        mockAnswer.setContent("mockContent");
        return mockAnswer;
    }

    public List<Answer> findAnswers() {
        List<Answer> mockAnswerList = new ArrayList<>();

        Answer answerRes1 = new Answer(1L, "title1","contents1");
        Answer answerRes2 = new Answer(2L, "title2","contents2");
        Answer answerRes3 = new Answer(3L, "title3","contents3");
        Answer answerRes4 = new Answer(4L, "title4","contents4");
        Answer answerRes5 = new Answer(5L, "title5","contents5");
        mockAnswerList.add(answerRes1);
        mockAnswerList.add(answerRes2);
        mockAnswerList.add(answerRes3);
        mockAnswerList.add(answerRes4);
        mockAnswerList.add(answerRes5);
        return mockAnswerList;
    }

    public void deleteAnswer(Long answerId) {
    }


//   실제 service
//    public Answer createAnswer(Answer answer) {
//        return answerRepository.save(answer);
//    }

//    public Answer updateAnswer(Answer answer) {
//        Optional<Answer> optionalAnswer = answerRepository.findById(answer.getAnswerId());
//        Answer verifiedAnswer = optionalAnswer.orElseThrow(()-> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
//
//        Optional.ofNullable(answer.getAnswerId()).ifPresent(answerId -> verifiedAnswer.setAnswerId(answerId));
//        Optional.ofNullable(answer.getTitle()).ifPresent(title -> verifiedAnswer.setTitle(title));
//        Optional.ofNullable(answer.getContent()).ifPresent(content -> verifiedAnswer.setContent(content));
//
//        return answerRepository.save(verifiedAnswer);
//    }

//    public Answer findAnswer(Long answerId) {
//
//        return null;
//    }

//    public List<Answer> findAnswers() {
//
//        return null;
//    }

//    public void deleteAnswer(Long answerId) {
//        Answer answer = new Answer();
//        answerRepository.delete(answer);
//    }
}

