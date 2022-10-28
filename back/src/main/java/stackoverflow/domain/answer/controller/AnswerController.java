package stackoverflow.domain.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.AnswerAccountResDto;
import stackoverflow.domain.answer.dto.AnswerReqDto;
import stackoverflow.domain.answer.dto.AnswerResDto;
import stackoverflow.domain.answer.service.AnswerService;
import stackoverflow.global.common.dto.PageDto;
import stackoverflow.global.common.dto.SingleResDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/answers")
@RestController
public class AnswerController {
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<SingleResDto<String>> postAnswer(@RequestBody AnswerReqDto answerReqDto) {

        return new ResponseEntity<>(new SingleResDto<>("success create answer"), HttpStatus.CREATED);
    }


    @PatchMapping("/{answerId}")
    public ResponseEntity<SingleResDto<String>> patchAnswer(@PathVariable Long answerId, @RequestBody AnswerReqDto answerReqDto) {

        return new ResponseEntity<>(new SingleResDto<>("success modify answer"), HttpStatus.OK);
    }


    @GetMapping("/{answerId}")
    public ResponseEntity getAnswer(@PathVariable Long answerId) {

        AnswerAccountResDto account = new AnswerAccountResDto();
        account.setId(1L);
        account.setEmail("account@gmail.com");
        account.setPath("profile");
        account.setNickname("nickname");
        AnswerResDto answerResDto = new AnswerResDto(answerId, "content",10, account);
        answerResDto.setCreatedAt(LocalDateTime.now());
        answerResDto.setModifiedAt(LocalDateTime.now());

        return new ResponseEntity<>(answerResDto, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<PageDto> getAnswers(Pageable pageable) {
        List<AnswerResDto> list = new ArrayList<>();

        for(int i =1 ; i <=10 ; i++) {
            AnswerAccountResDto account = new AnswerAccountResDto();
            account.setId(100L+i);
            account.setEmail("mock"+i+"@gmail.com");
            account.setPath("profile"+i);
            account.setNickname("nick"+i);
            AnswerResDto answerResDto = new AnswerResDto(0L+i, "contents"+i, 2, account);
            answerResDto.setCreatedAt(LocalDateTime.now());
            answerResDto.setModifiedAt(LocalDateTime.now());

            list.add(answerResDto);
        }
        Page<AnswerResDto> page = new PageImpl<>(list, pageable, 10);

        return new ResponseEntity<>(new PageDto<>(page), HttpStatus.OK);
    }


    @DeleteMapping("/{answerId}")
    public ResponseEntity<SingleResDto<String>> deleteAnswer(@PathVariable Long answerId) {

        return new ResponseEntity<>(new SingleResDto<>("success delete answer"), HttpStatus.OK);  /// 추후 NO_CONTENT로 변경해야함
    }
}
