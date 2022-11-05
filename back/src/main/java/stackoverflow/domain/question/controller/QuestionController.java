package stackoverflow.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.question.dto.*;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.entity.QuestionVote;
import stackoverflow.domain.question.service.QuestionService;
import stackoverflow.global.argumentreslover.LoginAccountId;
import stackoverflow.global.common.dto.PageDto;
import stackoverflow.global.common.dto.SingleResDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<AddQuestionResDto> questionAdd(@LoginAccountId Long loginAccountId,
                                                         @Valid @RequestBody AddQuestionReqDto addQuestionReqDto) {

        Question question = addQuestionReqDto.toQuestion(loginAccountId);
        Question addedQuestion = questionService.addQuestion(question);

        return new ResponseEntity<>(new AddQuestionResDto(addedQuestion.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{questionId}")
    public ResponseEntity<SingleResDto<String>> questionModify(@LoginAccountId Long loginAccountId,
                                                               @PathVariable Long questionId,
                                                               @RequestBody ModifyQuestionReqDto modifyQuestionReqDto) {

        Question question = modifyQuestionReqDto.toQuestion(loginAccountId, questionId);
        questionService.modifyQuestion(question);

        return new ResponseEntity<>(new SingleResDto<>("success modify question"), HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<SingleResDto<String>> questionRemove(@LoginAccountId Long loginAccountId,
                                                               @PathVariable Long questionId) {

        questionService.removeQuestion(loginAccountId, questionId);

        return new ResponseEntity<>(new SingleResDto<>("success delete question"), HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResDto> questionDetails(@PathVariable Long questionId) {

        Question question = questionService.findQuestion(questionId);
        QuestionResDto questionResDto = new QuestionResDto(question);

        return new ResponseEntity<>(questionResDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageDto<QuestionsResDto>> questionList(Pageable pageable,
                                                                 @RequestParam(required = false, defaultValue = "") String keyword) {

        Page<Question> questions = questionService.findQuestions(keyword, pageable);
        Page<QuestionsResDto> questionsRes = questions.map(QuestionsResDto::new);

        return new ResponseEntity<>(new PageDto<>(questionsRes), HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<PageDto<QuestionsResDto>> accountQuestionList(@PathVariable Long accountId, Pageable pageable) {

        Page<Question> questions = questionService.findAccountQuestions(accountId, pageable);
        Page<QuestionsResDto> questionsRes = questions.map(QuestionsResDto::new);

        return new ResponseEntity<>(new PageDto<>(questionsRes), HttpStatus.OK);
    }

    @GetMapping("/unAnswered")
    public ResponseEntity<PageDto<QuestionsResDto>> unAnsweredQuestionList(Pageable pageable,
                                                                           @RequestParam(required = false, defaultValue = "") String keyword) {

        Page<Question> unAnsweredQuestions = questionService.findUnAnsweredQuestions(keyword, pageable);
        Page<QuestionsResDto> questionRes = unAnsweredQuestions.map(QuestionsResDto::new);

        return new ResponseEntity<>(new PageDto<>(questionRes), HttpStatus.OK);
    }

    @GetMapping("/totalVote")
    public ResponseEntity<PageDto<QuestionsResDto>> totalVoteQuestionList(Pageable pageable,
                                                                          @RequestParam(required = false, defaultValue = "") String keyword) {
        Page<QuestionsResDto> totalVoteQuestions = questionService.findTotalVoteQuestions(keyword, pageable);
        return new ResponseEntity<>(new PageDto<>(totalVoteQuestions), HttpStatus.OK);
    }

    @PostMapping("/questionVote/{questionId}")
    public ResponseEntity<SingleResDto<String>> questionVote(@LoginAccountId Long loginAccountId,
                                                                @PathVariable Long questionId,
                                                                @RequestBody QuestionVoteReqDto questionVoteReqDto) {

        QuestionVote questionVote = questionVoteReqDto.toQuestionVote(loginAccountId, questionId);
        String votedQuestion = questionService.voteQuestion(questionVote);

        return new ResponseEntity<>(new SingleResDto<>(votedQuestion), HttpStatus.CREATED);
    }
}
