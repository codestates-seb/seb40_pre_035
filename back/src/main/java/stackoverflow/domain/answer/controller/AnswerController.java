package stackoverflow.domain.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.answer.dto.AddAnswerVoteReqDto;
import stackoverflow.domain.answer.dto.AnswerReqDto;
import stackoverflow.domain.answer.dto.AnswerResDto;
import stackoverflow.domain.answer.dto.QuestionAnswerResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.entity.AnswerVote;
import stackoverflow.domain.answer.service.AnswerService;
import stackoverflow.global.argumentreslover.LoginAccountId;
import stackoverflow.global.common.dto.PageDto;
import stackoverflow.global.common.dto.SingleResDto;

@RequiredArgsConstructor
@RequestMapping("/answers")
@RestController
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<SingleResDto<String>> answerAdd(@LoginAccountId Long loginAccountId,
                                                                 @RequestBody AnswerReqDto answerReqDto) {
        answerReqDto.setAccountId(loginAccountId);
        Answer answer = answerReqDto.toAnswer();
        answerService.addAnswer(answer);

        return new ResponseEntity<>(new SingleResDto<>("success create answer"), HttpStatus.CREATED);
    }


    @PatchMapping("/{answerId}")
    public ResponseEntity<SingleResDto<String>> answerModify(@LoginAccountId Long loginAccountId,
                                                                  @PathVariable Long answerId,
                                                                  @RequestBody AnswerReqDto answerReqDto) {
        answerReqDto.setAccountId(loginAccountId);
        answerReqDto.setAnswerId(answerId);
        Answer answer = answerReqDto.toAnswer();
        answerService.modifyAnswer(answer);

        return new ResponseEntity<>(new SingleResDto<>("success modify question"), HttpStatus.OK);
    }


    @GetMapping("/{answerId}")
    public ResponseEntity answerDetails(@PathVariable Long answerId) {
        Answer answer = answerService.findAnswer(answerId);
        AnswerResDto answerResDto = new AnswerResDto(answer);

        return new ResponseEntity<>(answerResDto, HttpStatus.OK);
    }


    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<PageDto> answerList(Pageable pageable) {
        Page<Answer> page = answerService.findAnswers(pageable);
        Page<AnswerResDto> accountAnswersDtePage = page.map(AnswerResDto::new);

        return new ResponseEntity<>(new PageDto<>(accountAnswersDtePage), HttpStatus.OK);
    }


    @DeleteMapping("/{answerId}")
    public ResponseEntity<SingleResDto<String>> answerRemove(@LoginAccountId Long loginAccountId,
                                                             @PathVariable Long answerId) {

        answerService.removeAnswer(loginAccountId, answerId);

        return new ResponseEntity<>(new SingleResDto<>("success delete answer"), HttpStatus.OK);
    }


    @PostMapping("/answerVote/{answerId}")
    public ResponseEntity<SingleResDto<String>> answerVote(@LoginAccountId Long loginAccountId,
                                                              @PathVariable Long answerId,
                                                              @RequestBody AddAnswerVoteReqDto addAnswerVoteReqDto) {

        AnswerVote answerVote = addAnswerVoteReqDto.toAnswerVote(loginAccountId, answerId);
        String votedAnswer = answerService.voteAnswer(answerVote);

        return new ResponseEntity<>(new SingleResDto<>(votedAnswer), HttpStatus.CREATED);
    }

    @PostMapping("/select/{answerId}")
    public ResponseEntity<SingleResDto<String>> answerSelect(@LoginAccountId Long loginAccountId,
                                                             @PathVariable Long answerId) {

        answerService.selectAnswer(loginAccountId, answerId);

        return new ResponseEntity<>(new SingleResDto<>("success select answer"), HttpStatus.CREATED);
    }


    @GetMapping("/account/{accountId}")
    public ResponseEntity<PageDto> accountAnswerList(@PathVariable Long accountId, Pageable pageable) {

        Page<Answer> page = answerService.findAccountAnswers(accountId, pageable);
        Page<AnswerResDto> pageDto= page.map(answer-> new AnswerResDto(answer));

        return new ResponseEntity<>(new PageDto(pageDto), HttpStatus.OK);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<PageDto<QuestionAnswerResDto>> questionAnswerList(@PathVariable Long questionId,
                                                                             Pageable pageable) {

        Page<Answer> questionAnswers = answerService.findQuestionAnswers(questionId, pageable);
        Page<QuestionAnswerResDto> questionAnswersRes = questionAnswers.map(QuestionAnswerResDto::new);

        return new ResponseEntity<>(new PageDto<>(questionAnswersRes), HttpStatus.OK);
    }
}