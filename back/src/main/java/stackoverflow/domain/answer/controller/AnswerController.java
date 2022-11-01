package stackoverflow.domain.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.AnswerAccountResDto;
import stackoverflow.domain.answer.dto.AddAnswerVoteReqDto;
import stackoverflow.domain.answer.dto.AnswerReqDto;
import stackoverflow.domain.answer.dto.AnswerResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.service.AnswerService;
import stackoverflow.global.argumentreslover.LoginAccountId;
import stackoverflow.global.common.dto.PageDto;
import stackoverflow.global.common.dto.SingleResDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/answers")
@RestController
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<SingleResDto<AnswerResDto>> postAnswer(@LoginAccountId Long loginAccountId,
                                                                 @RequestBody AnswerReqDto answerReqDto) {
        Answer answer = answerReqDto.toAnswer();
        answer.getAccount().setId(loginAccountId);
        Answer createdAnswer = answerService.createAnswer(answer);
        AnswerResDto response = new AnswerResDto(createdAnswer);

        return new ResponseEntity<>(new SingleResDto<>(response), HttpStatus.CREATED);
//        return new ResponseEntity<>(new SingleResDto<>("success create answer"), HttpStatus.CREATED);
    }



    @Transactional
    @PatchMapping("/{answerId}")
    public ResponseEntity<SingleResDto<AnswerResDto>> patchAnswer(@LoginAccountId Long loginAccountId,
                                                                  @PathVariable Long answerId,
                                                                  @RequestBody AnswerReqDto answerReqDto) {
        Answer answer = answerReqDto.toAnswer();
        answer.getAccount().setId(loginAccountId);
        answer.setId(answerId);
        Answer updatedAnswer = answerService.updateAnswer(answer);
        AnswerResDto response = new AnswerResDto(updatedAnswer);

        return new ResponseEntity<>(new SingleResDto<>(response), HttpStatus.OK);
//        return new ResponseEntity<>(new SingleResDto<>("success modify question"), HttpStatus.OK);
    }


    @Transactional(readOnly = true)
    @GetMapping("/{answerId}")
    public ResponseEntity getAnswer(@PathVariable Long answerId) {
        Answer answer = answerService.findAnswer(answerId);
        AnswerResDto answerResDto = new AnswerResDto(answer);  // 이 부분 수정됨

        return new ResponseEntity<>(new SingleResDto<>(answerResDto), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<PageDto> getAnswers(Pageable pageable) {
        Page<Answer> page = answerService.findAnswers(pageable);
        Page<AnswerResDto> response = page.map(answer -> new AnswerResDto(answer));

        return new ResponseEntity<>(new PageDto<>(response), HttpStatus.OK);
    }


    @DeleteMapping("/{answerId}")
    public ResponseEntity<SingleResDto<String>> deleteAnswer(@PathVariable Long answerId) {

        return new ResponseEntity<>(new SingleResDto<>("success delete answer"), HttpStatus.OK);  /// 추후 NO_CONTENT로 변경해야함
    }



    @PostMapping("/answerVote/{answerId}")
    public ResponseEntity<SingleResDto<String>> addAnswerVote(@PathVariable Long answerId, @RequestBody AddAnswerVoteReqDto addAnswerVoteReqDto) {

        return new ResponseEntity<>(new SingleResDto<>("success add Vote"), HttpStatus.CREATED);
    }


    @Transactional(readOnly = true)
    @GetMapping("/account/{accountId}")
    public ResponseEntity<PageDto> getAccountAnswers(@PathVariable Long accountId, Pageable pageable) {

        Page<Answer> page = answerService.findAccountAnswers(accountId, pageable);
        Page<AnswerResDto> pageDto= page.map(answer-> new AnswerResDto(answer));

        return new ResponseEntity<>(new PageDto(pageDto), HttpStatus.OK);
    }
}