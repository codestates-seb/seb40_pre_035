package stackoverflow.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.QuestionAccountResDto;
import stackoverflow.domain.question.dto.QuestionVoteReqDto;
import stackoverflow.domain.question.dto.QuestionReqDto;
import stackoverflow.domain.question.dto.QuestionResDto;
import stackoverflow.domain.question.dto.QuestionsResDto;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.entity.QuestionVote;
import stackoverflow.domain.question.service.QuestionService;
import stackoverflow.global.argumentreslover.LoginAccountId;
import stackoverflow.global.common.dto.PageDto;
import stackoverflow.global.common.dto.SingleResDto;

import javax.validation.Valid;

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
    public ResponseEntity<SingleResDto<String>> questionRemove(@LoginAccountId Long loginAccountId,
                                                               @PathVariable Long questionId) {

        QuestionReqDto questionReqDto = new QuestionReqDto();
        questionReqDto.setQuestionId(questionId);
        questionReqDto.setAccountId(loginAccountId);

        Question question = questionReqDto.toQuestion();

        questionService.removeQuestion(question);

        return new ResponseEntity<>(new SingleResDto<>("success delete question"), HttpStatus.OK);
    }

    @GetMapping("/questions/{questionId}")
    public ResponseEntity<QuestionResDto> questionDetails(@PathVariable Long questionId) {

        Question question = questionService.findQuestion(questionId);

        QuestionResDto questionResDto = new QuestionResDto(question);

        return new ResponseEntity<>(questionResDto, HttpStatus.OK);
    }

    @GetMapping("/questions")
    public ResponseEntity<PageDto<QuestionsResDto>> questionList(Pageable pageable,
                                                                 @RequestParam(required = false, defaultValue = "") String keyword) {

        Page<Question> questions = questionService.findQuestions(keyword, pageable);
        Page<QuestionsResDto> questionsRes = questions.map(QuestionsResDto::new);
        return new ResponseEntity<>(new PageDto<>(questionsRes), HttpStatus.OK);
    }

    @GetMapping("/questions/account/{accountId}")
    public ResponseEntity<PageDto<QuestionsResDto>> AccountQuestionList(@PathVariable Long accountId, Pageable pageable) {

        Page<Question> questions = questionService.findAccountQuestions(accountId, pageable);
        Page<QuestionsResDto> questionsRes = questions.map(QuestionsResDto::new);
        return new ResponseEntity<>(new PageDto<>(questionsRes), HttpStatus.OK);
    }

    @GetMapping("/questions/unAnswered")
    public ResponseEntity<PageDto<QuestionsResDto>> getQuestionsUnAnswered(Pageable pageable,
                                                                           @RequestParam(required = false) String keyword) {

        Page<Question> unAnsweredQuestions = questionService.findUnAnsweredQuestions(keyword, pageable);
        Page<QuestionsResDto> questionRes = unAnsweredQuestions.map(QuestionsResDto::new);
        return new ResponseEntity<>(new PageDto<>(questionRes), HttpStatus.OK);
    }

    @PostMapping("/questionVote/{questionId}")
    public ResponseEntity<SingleResDto<String>> questionVote(@LoginAccountId Long loginAccountId,
                                                                @PathVariable Long questionId,
                                                                @RequestBody QuestionVoteReqDto questionVoteReqDto) {

        QuestionVote questionVote = questionVoteReqDto.toQuestionVote(loginAccountId, questionId);
        questionService.voteQuestion(questionVote);
        return new ResponseEntity<>(new SingleResDto<>("success vote"), HttpStatus.CREATED);
    }
}
