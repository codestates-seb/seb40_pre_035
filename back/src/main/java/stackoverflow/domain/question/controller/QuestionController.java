package stackoverflow.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        answerAccountRes.setEmail("mock2@mock.com");
        answerAccountRes.setPath("mock/mock2");
        answerAccountRes.setNickname("mockNickname2");
        answerAccountRes.setCreatedAt(LocalDateTime.now());
        answerAccountRes.setModifiedAt(LocalDateTime.now());

        answerRes1.setId(4L);
        answerRes1.setTitle("testAnswerTitle4");
        answerRes1.setContent("testAnswerContent4");
        answerRes1.setAccount(answerAccountRes);
        answerRes1.setCreatedAt(LocalDateTime.now());
        answerRes1.setModifiedAt(LocalDateTime.now());

        answerRes2.setId(5L);
        answerRes2.setTitle("testAnswerTitle5");
        answerRes2.setContent("testAnswerContent5");
        answerRes2.setAccount(answerAccountRes);
        answerRes2.setCreatedAt(LocalDateTime.now());
        answerRes2.setModifiedAt(LocalDateTime.now());

        answerResList.add(answerRes1);
        answerResList.add(answerRes2);

        questionRes.setId(3L);
        questionRes.setTitle("testQuestionTitle3");
        questionRes.setContent("testQuestionContent3");
        questionRes.setTotalVote(10);
        questionRes.setAccount(questionAccountRes);
        questionRes.setAnswers(answerResList);
        questionRes.setCreatedAt(LocalDateTime.now());
        questionRes.setModifiedAt(LocalDateTime.now());

        return questionRes;
    }

    @GetMapping("/questions")
    public Page<QuestionRes> getQuestions(Pageable pageable) {
        List<QuestionRes> questionResList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            QuestionRes questionRes = new QuestionRes();
            QuestionAccountRes questionAccountRes = new QuestionAccountRes();
            QuestionAccountRes answerAccountRes = new QuestionAccountRes();
            List<QuestionAnswerRes> answerResList = new ArrayList<>();
            QuestionAnswerRes answerRes1 = new QuestionAnswerRes();
            QuestionAnswerRes answerRes2 = new QuestionAnswerRes();

            questionAccountRes.setId(1L + (i * 5));
            questionAccountRes.setEmail("mock" + (i * 5) + "@mock.com");
            questionAccountRes.setPath("mock/mock" + (i * 5));
            questionAccountRes.setNickname("mockNickname" + (i * 5));
            questionAccountRes.setCreatedAt(LocalDateTime.now());
            questionAccountRes.setModifiedAt(LocalDateTime.now());

            answerAccountRes.setId(2L + (i * 5));
            answerAccountRes.setEmail("mock" + (2 + i * 5) + "@test.com");
            answerAccountRes.setPath("mock/mock" + (2 + i * 5));
            answerAccountRes.setNickname("mockNickname" + (2 + i * 5));
            answerAccountRes.setCreatedAt(LocalDateTime.now());
            answerAccountRes.setModifiedAt(LocalDateTime.now());

            answerRes1.setId(4L + (i * 5));
            answerRes1.setTitle("testAnswerTitle" + (4 + i * 5));
            answerRes1.setContent("testAnswerContent" + (4 + i * 5));
            answerRes1.setAccount(answerAccountRes);
            answerRes1.setCreatedAt(LocalDateTime.now());
            answerRes1.setModifiedAt(LocalDateTime.now());

            answerRes2.setId(5L + (i * 5));
            answerRes2.setTitle("testAnswerTitle" + (5 + i * 5));
            answerRes2.setContent("testAnswerContent" + (5 + i * 5));
            answerRes2.setAccount(answerAccountRes);
            answerRes2.setCreatedAt(LocalDateTime.now());
            answerRes2.setModifiedAt(LocalDateTime.now());

            answerResList.add(answerRes1);
            answerResList.add(answerRes2);

            questionRes.setId(3L + (i * 5));
            questionRes.setTitle("testQuestionTitle" + (3 + i * 5));
            questionRes.setContent("testQuestionContent" + (3 + i * 5));
            questionRes.setTotalVote(10);
            questionRes.setAccount(questionAccountRes);
            questionRes.setAnswers(answerResList);
            questionRes.setCreatedAt(LocalDateTime.now());
            questionRes.setModifiedAt(LocalDateTime.now());

            questionResList.add(questionRes);
        }

        return new PageImpl<>(questionResList, pageable, 100);
    }

    @GetMapping("/questions/search")
    public Page<QuestionRes> searchQuestions(Pageable pageable, @RequestParam String keyword) {
        List<QuestionRes> questionResList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            QuestionRes questionRes = new QuestionRes();
            QuestionAccountRes questionAccountRes = new QuestionAccountRes();
            QuestionAccountRes answerAccountRes = new QuestionAccountRes();
            List<QuestionAnswerRes> answerResList = new ArrayList<>();
            QuestionAnswerRes answerRes1 = new QuestionAnswerRes();
            QuestionAnswerRes answerRes2 = new QuestionAnswerRes();

            questionAccountRes.setId(1L + (i * 5));
            questionAccountRes.setEmail("mock" + (i * 5) + "@mock.com");
            questionAccountRes.setPath("mock/mock" + (i * 5));
            questionAccountRes.setNickname("mockNickname" + (i * 5));
            questionAccountRes.setCreatedAt(LocalDateTime.now());
            questionAccountRes.setModifiedAt(LocalDateTime.now());

            answerAccountRes.setId(2L + (i * 5));
            answerAccountRes.setEmail("mock" + (2 + i * 5) + "@test.com");
            answerAccountRes.setPath("mock/mock" + (2 + i * 5));
            answerAccountRes.setNickname("mockNickname" + (2 + i * 5));
            answerAccountRes.setCreatedAt(LocalDateTime.now());
            answerAccountRes.setModifiedAt(LocalDateTime.now());

            answerRes1.setId(4L + (i * 5));
            answerRes1.setTitle("testAnswerTitle" + (4 + i * 5));
            answerRes1.setContent("testAnswerContent" + (4 + i * 5));
            answerRes1.setAccount(answerAccountRes);
            answerRes1.setCreatedAt(LocalDateTime.now());
            answerRes1.setModifiedAt(LocalDateTime.now());

            answerRes2.setId(5L + (i * 5));
            answerRes2.setTitle("testAnswerTitle" + (5 + i * 5));
            answerRes2.setContent("testAnswerContent" + (5 + i * 5));
            answerRes2.setAccount(answerAccountRes);
            answerRes2.setCreatedAt(LocalDateTime.now());
            answerRes2.setModifiedAt(LocalDateTime.now());

            answerResList.add(answerRes1);
            answerResList.add(answerRes2);

            questionRes.setId(3L + (i * 5));
            questionRes.setTitle("testQuestionTitle" + (3 + i * 5));
            questionRes.setContent("testQuestionContent" + (3 + i * 5));
            questionRes.setTotalVote(10);
            questionRes.setAccount(questionAccountRes);
            questionRes.setAnswers(answerResList);
            questionRes.setCreatedAt(LocalDateTime.now());
            questionRes.setModifiedAt(LocalDateTime.now());

            questionResList.add(questionRes);
        }

        return new PageImpl<>(questionResList, pageable, 100);
    }
}
