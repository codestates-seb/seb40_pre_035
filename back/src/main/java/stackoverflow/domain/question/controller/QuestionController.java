package stackoverflow.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.QuestionAccountRes;
import stackoverflow.domain.question.dto.QuestionReq;
import stackoverflow.domain.question.dto.QuestionRes;
import stackoverflow.global.common.dto.PageDto;

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

        questionAccountRes.setId(1L);
        questionAccountRes.setEmail("mock1@mock.com");
        questionAccountRes.setProfile("mock/mock1");
        questionAccountRes.setNickname("mockNickname1");

        questionRes.setId(3L);
        questionRes.setTitle("testQuestionTitle3");
        questionRes.setContent("testQuestionContent3");
        questionRes.setTotalVote(10);
        questionRes.setAccount(questionAccountRes);
        questionRes.setCreatedAt(LocalDateTime.now());
        questionRes.setModifiedAt(LocalDateTime.now());

        return questionRes;
    }

    @GetMapping("/questions")
    public PageDto<QuestionRes> getQuestions(Pageable pageable) {
        List<QuestionRes> questionResList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            QuestionRes questionRes = new QuestionRes();
            QuestionAccountRes questionAccountRes = new QuestionAccountRes();
            QuestionAccountRes answerAccountRes = new QuestionAccountRes();

            questionAccountRes.setId(1L + (i * 5));
            questionAccountRes.setEmail("mock" + (i * 5) + "@mock.com");
            questionAccountRes.setProfile("mock/mock" + (i * 5));
            questionAccountRes.setNickname("mockNickname" + (i * 5));

            answerAccountRes.setId(2L + (i * 5));
            answerAccountRes.setEmail("mock" + (2 + i * 5) + "@test.com");
            answerAccountRes.setProfile("mock/mock" + (2 + i * 5));
            answerAccountRes.setNickname("mockNickname" + (2 + i * 5));

            questionRes.setId(3L + (i * 5));
            questionRes.setTitle("testQuestionTitle" + (3 + i * 5));
            questionRes.setContent("testQuestionContent" + (3 + i * 5));
            questionRes.setTotalVote(10);
            questionRes.setAccount(questionAccountRes);
            questionRes.setCreatedAt(LocalDateTime.now());
            questionRes.setModifiedAt(LocalDateTime.now());

            questionResList.add(questionRes);
        }

        PageImpl<QuestionRes> questionRes = new PageImpl<>(questionResList, pageable, 100);
        return new PageDto<>(questionRes);
    }

    @GetMapping("/questions/search")
    public PageDto<QuestionRes> searchQuestions(Pageable pageable, @RequestParam String keyword) {
        List<QuestionRes> questionResList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            QuestionRes questionRes = new QuestionRes();
            QuestionAccountRes questionAccountRes = new QuestionAccountRes();
            QuestionAccountRes answerAccountRes = new QuestionAccountRes();

            questionAccountRes.setId(1L + (i * 5));
            questionAccountRes.setEmail("mock" + (i * 5) + "@mock.com");
            questionAccountRes.setProfile("mock/mock" + (i * 5));
            questionAccountRes.setNickname("mockNickname" + (i * 5));

            answerAccountRes.setId(2L + (i * 5));
            answerAccountRes.setEmail("mock" + (2 + i * 5) + "@test.com");
            answerAccountRes.setProfile("mock/mock" + (2 + i * 5));
            answerAccountRes.setNickname("mockNickname" + (2 + i * 5));

            questionRes.setId(3L + (i * 5));
            questionRes.setTitle("testQuestionTitle" + (3 + i * 5));
            questionRes.setContent("testQuestionContent" + (3 + i * 5));
            questionRes.setTotalVote(10);
            questionRes.setAccount(questionAccountRes);
            questionRes.setCreatedAt(LocalDateTime.now());
            questionRes.setModifiedAt(LocalDateTime.now());

            questionResList.add(questionRes);
        }

        PageImpl<QuestionRes> questionRes = new PageImpl<>(questionResList, pageable, 100);
        return new PageDto<>(questionRes);
    }
}
