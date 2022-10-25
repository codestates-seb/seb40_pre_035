package stackoverflow.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.QuestionAccountRes;
import stackoverflow.domain.answer.dto.QuestionAnswerRes;
import stackoverflow.domain.question.dto.QuestionReq;
import stackoverflow.domain.question.dto.QuestionRes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    @PostMapping("/question")
    public String createQuestion(@RequestBody QuestionReq questionReq) {
        return "success create question";
    }

    @PatchMapping("/question/{questionId}")
    public String modifyQuestion(@PathVariable Long questionId) {
        return "success modify question";
    }

    @DeleteMapping("/question/{questionId}")
    public String deleteQuestion(@PathVariable Long questionId) {
        return "success delete question";
    }

    @GetMapping("/question/{questionId}")
    public QuestionRes getQuestion(@PathVariable Long questionId) {

        QuestionRes questionRes = new QuestionRes();
        QuestionAccountRes questionAccountRes = new QuestionAccountRes();
        QuestionAccountRes answerAccountRes = new QuestionAccountRes();
        List<QuestionAnswerRes> answerResList = new ArrayList<>();
        QuestionAnswerRes answerRes1 = new QuestionAnswerRes();
        QuestionAnswerRes answerRes2 = new QuestionAnswerRes();

        questionAccountRes.setId(1L);
        questionAccountRes.setEmail("mock1@mock.com");
        questionAccountRes.setPath("mock/mock1");
        questionAccountRes.setNickname("mockNickname1");
        questionAccountRes.setCreatedAt(LocalDateTime.now());
        questionAccountRes.setModifiedAt(LocalDateTime.now());

        answerAccountRes.setId(2L);
        answerAccountRes.setEmail("mock1@test.com");
        answerAccountRes.setPath("mock/mock2");
        answerAccountRes.setNickname("mockNickname1");
        answerAccountRes.setCreatedAt(LocalDateTime.now());
        answerAccountRes.setModifiedAt(LocalDateTime.now());

        answerRes1.setId(4L);
        answerRes1.setTitle("testAnswerTitle1");
        answerRes1.setContent("testAnswerContent");
        answerRes1.setAccount(answerAccountRes);
        answerRes1.setCreatedAt(LocalDateTime.now());
        answerRes1.setModifiedAt(LocalDateTime.now());

        answerRes2.setId(5L);
        answerRes2.setTitle("testAnswerTitle1");
        answerRes2.setContent("testAnswerContent");
        answerRes2.setAccount(answerAccountRes);
        answerRes2.setCreatedAt(LocalDateTime.now());
        answerRes2.setModifiedAt(LocalDateTime.now());

        answerResList.add(answerRes1);
        answerResList.add(answerRes2);

        questionRes.setId(3L);
        questionRes.setTitle("testQuestionTitle1");
        questionRes.setContent("testQuestionContent");
        questionRes.setTotalVote(10);
        questionRes.setAccount(questionAccountRes);
        questionRes.setAnswers(answerResList);
        questionRes.setCreatedAt(LocalDateTime.now());
        questionRes.setModifiedAt(LocalDateTime.now());

        return questionRes;
    }

//    @GetMapping("/questions")
//    public Page<QuestionRes> getQuestions() {
//
//    }
//
//    @GetMapping("/questions")
//    public Page<QuestionRes> searchQuestions() {
//
//    }
}
