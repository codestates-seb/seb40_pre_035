package stackoverflow.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.QuestionAccountResDto;
import stackoverflow.domain.question.dto.AddQuestionVoteReqDto;
import stackoverflow.domain.question.dto.QuestionReqDto;
import stackoverflow.domain.question.dto.QuestionResDto;
import stackoverflow.domain.question.dto.QuestionsResDto;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.service.QuestionService;
import stackoverflow.global.argumentreslover.LoginAccountId;
import stackoverflow.global.common.dto.PageDto;
import stackoverflow.global.common.dto.SingleResDto;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/questions")
    public ResponseEntity<SingleResDto<String>> questionAdd(@LoginAccountId Long loginAccountId,
                                                               @Valid @RequestBody QuestionReqDto questionReqDto) {

        questionReqDto.setAccountId(loginAccountId);
        Question question = questionReqDto.toQuestion();

        questionService.addQuestion(question);

        return new ResponseEntity<>(new SingleResDto<>("success create question"), HttpStatus.CREATED);
    }

    @PatchMapping("/questions/{questionId}")
    public ResponseEntity<SingleResDto<String>> questionModify(@LoginAccountId Long loginAccountId,
                                                               @PathVariable Long questionId,
                                                               @RequestBody QuestionReqDto questionReqDto) {

        questionReqDto.setAccountId(loginAccountId);
        questionReqDto.setQuestionId(questionId);

        Question question = questionReqDto.toQuestion();

        questionService.modifyQuestion(question);

        return new ResponseEntity<>(new SingleResDto<>("success modify question"), HttpStatus.OK);
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<SingleResDto<String>> deleteQuestion(@PathVariable Long questionId) {
        return new ResponseEntity<>(new SingleResDto<>("success delete question"), HttpStatus.OK);
    }

    @GetMapping("/questions/{questionId}")
    public ResponseEntity<QuestionResDto> questionDetails(@PathVariable Long questionId) {

        Question question = questionService.findQuestion(questionId);

        QuestionResDto questionResDto = new QuestionResDto(question);

        return new ResponseEntity<>(questionResDto, HttpStatus.OK);
    }

    @GetMapping("/questions")
    public ResponseEntity<PageDto<QuestionsResDto>> getQuestions(Pageable pageable,
                                                                 @RequestParam(required = false) String keyword) {
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
        return new ResponseEntity<>(new PageDto<>(questionRes), HttpStatus.OK);
    }

    @GetMapping("/questions/account/{accountId}")
    public ResponseEntity<PageDto<QuestionsResDto>> getQuestionsAccount(@PathVariable Long accountId, Pageable pageable) {
        List<QuestionsResDto> questionsResDtoList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            QuestionsResDto questionsResDto = new QuestionsResDto();
            QuestionAccountResDto questionAccountResDto = new QuestionAccountResDto();

            questionAccountResDto.setId(1L);
            questionAccountResDto.setEmail("mock1@mock.com");
            questionAccountResDto.setProfile("mock/mock1");
            questionAccountResDto.setNickname("mockNickname1");

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
        return new ResponseEntity<>(new PageDto<>(questionRes), HttpStatus.OK);
    }

    @GetMapping("/questions/unAnswered")
    public ResponseEntity<PageDto<QuestionsResDto>> getQuestionsUnAnswered(Pageable pageable,
                                                                           @RequestParam(required = false) String keyword) {
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
            questionsResDto.setSelectedAnswer(false);
            questionsResDto.setAccount(questionAccountResDto);
            questionsResDto.setCreatedAt(LocalDateTime.now());
            questionsResDto.setModifiedAt(LocalDateTime.now());

            questionsResDtoList.add(questionsResDto);
        }

        PageImpl<QuestionsResDto> questionRes = new PageImpl<>(questionsResDtoList, pageable, 100);
        return new ResponseEntity<>(new PageDto<>(questionRes), HttpStatus.OK);
    }

    @PostMapping("/questionVote/{questionId}")
    public ResponseEntity<SingleResDto<String>> questionVoteAdd(@PathVariable Long questionId,
                                                                @RequestBody AddQuestionVoteReqDto addQuestionVoteReqDto) {
        return new ResponseEntity<>(new SingleResDto<>("success add vote"), HttpStatus.CREATED);
    }
}
