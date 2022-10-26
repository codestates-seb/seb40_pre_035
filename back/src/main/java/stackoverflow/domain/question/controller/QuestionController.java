package stackoverflow.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.QuestionAccountResDto;
import stackoverflow.domain.question.dto.QuestionReqDto;
import stackoverflow.domain.question.dto.QuestionResDto;
import stackoverflow.domain.question.dto.QuestionsResDto;
import stackoverflow.global.common.dto.PageDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    @PostMapping("/question")
    public String createQuestion(@RequestBody QuestionReqDto questionReqDto) {
        return "success create question";
    }

    @PatchMapping("/question/{questionId}")
    public String modifyQuestion(@PathVariable Long questionId, @RequestBody QuestionReqDto questionReqDto) {
        return "success modify question";
    }

    @DeleteMapping("/question/{questionId}")
    public String deleteQuestion(@PathVariable Long questionId) {
        return "success delete question";
    }

    @GetMapping("/question/{questionId}")
    public QuestionResDto getQuestion(@PathVariable Long questionId) {

        QuestionResDto questionResDto = new QuestionResDto();
        QuestionAccountResDto questionAccountResDto = new QuestionAccountResDto();

        questionAccountResDto.setId(1L);
        questionAccountResDto.setEmail("mock1@mock.com");
        questionAccountResDto.setProfile("mock/mock1");
        questionAccountResDto.setNickname("mockNickname1");

        questionResDto.setId(3L);
        questionResDto.setTitle("testQuestionTitle3");
        questionResDto.setContent("testQuestionContent3");
        questionResDto.setTotalVote(10);
        questionResDto.setAccount(questionAccountResDto);
        questionResDto.setCreatedAt(LocalDateTime.now());
        questionResDto.setModifiedAt(LocalDateTime.now());

        return questionResDto;
    }

    @GetMapping("/questions")
    public PageDto<QuestionsResDto> getQuestions(Pageable pageable) {
        List<QuestionsResDto> questionsResDtoList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            QuestionsResDto questionsResDto = new QuestionsResDto();
            QuestionAccountResDto questionAccountResDto = new QuestionAccountResDto();

            questionAccountResDto.setId(1L + (i * 5));
            questionAccountResDto.setEmail("mock" + (i * 5) + "@mock.com");
            questionAccountResDto.setProfile("mock/mock" + (i * 5));
            questionAccountResDto.setNickname("mockNickname" + (i * 5));

            questionsResDto.setId(3L + (i * 5));
            questionsResDto.setTitle("testQuestionTitle" + (3 + i * 5));
            questionsResDto.setContent("testQuestionContent" + (3 + i * 5));
            questionsResDto.setTotalVote(10);
            questionsResDto.setAnswerCount(5);
            questionsResDto.setSelectedAnswer(true);
            questionsResDto.setAccount(questionAccountResDto);
            questionsResDto.setCreatedAt(LocalDateTime.now());
            questionsResDto.setModifiedAt(LocalDateTime.now());

            questionsResDtoList.add(questionsResDto);
        }

        PageImpl<QuestionsResDto> questionRes = new PageImpl<>(questionsResDtoList, pageable, 100);
        return new PageDto<>(questionRes);
    }

    @GetMapping("/questions/search")
    public PageDto<QuestionsResDto> searchQuestions(Pageable pageable, @RequestParam String keyword) {
        List<QuestionsResDto> questionsResDtoList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            QuestionsResDto questionsResDto = new QuestionsResDto();
            QuestionAccountResDto questionAccountResDto = new QuestionAccountResDto();

            questionAccountResDto.setId(1L + (i * 5));
            questionAccountResDto.setEmail("mock" + (i * 5) + "@mock.com");
            questionAccountResDto.setProfile("mock/mock" + (i * 5));
            questionAccountResDto.setNickname("mockNickname" + (i * 5));

            questionsResDto.setId(3L + (i * 5));
            questionsResDto.setTitle("testQuestionTitle" + (3 + i * 5));
            questionsResDto.setContent("testQuestionContent" + (3 + i * 5));
            questionsResDto.setTotalVote(10);
            questionsResDto.setAnswerCount(5);
            questionsResDto.setSelectedAnswer(true);
            questionsResDto.setAccount(questionAccountResDto);
            questionsResDto.setCreatedAt(LocalDateTime.now());
            questionsResDto.setModifiedAt(LocalDateTime.now());

            questionsResDtoList.add(questionsResDto);
        }

        PageImpl<QuestionsResDto> questionRes = new PageImpl<>(questionsResDtoList, pageable, 100);
        return new PageDto<>(questionRes);
    }

    @GetMapping("/questions/account/{accountId}")
    public PageDto<QuestionsResDto> getQuestionsAccount(@PathVariable Long accountId, Pageable pageable) {
        List<QuestionsResDto> questionsResDtoList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            QuestionsResDto questionsResDto = new QuestionsResDto();
            QuestionAccountResDto questionAccountResDto = new QuestionAccountResDto();

            questionAccountResDto.setId(1L + (i * 5));
            questionAccountResDto.setEmail("mock" + (i * 5) + "@mock.com");
            questionAccountResDto.setProfile("mock/mock" + (i * 5));
            questionAccountResDto.setNickname("mockNickname" + (i * 5));

            questionsResDto.setId(3L + (i * 5));
            questionsResDto.setTitle("testQuestionTitle" + (3 + i * 5));
            questionsResDto.setContent("testQuestionContent" + (3 + i * 5));
            questionsResDto.setTotalVote(10);
            questionsResDto.setAnswerCount(5);
            questionsResDto.setSelectedAnswer(true);
            questionsResDto.setAccount(questionAccountResDto);
            questionsResDto.setCreatedAt(LocalDateTime.now());
            questionsResDto.setModifiedAt(LocalDateTime.now());

            questionsResDtoList.add(questionsResDto);
        }

        PageImpl<QuestionsResDto> questionRes = new PageImpl<>(questionsResDtoList, pageable, 100);
        return new PageDto<>(questionRes);
    }
}
