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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/answer")
@RestController
public class AnswerController {
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerReqDto answerReqDto) {
        String title = answerReqDto.getTitle();

        return new ResponseEntity<>(String.format("POST request accepted. title: %s", title), HttpStatus.CREATED);
    }


    @PatchMapping("/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable("answerId") Long id,
                                      @RequestBody AnswerReqDto answerReqDto) {

        return new ResponseEntity<>(String.format("PATCH request accepted. ID: %d", id), HttpStatus.OK);
    }


    @GetMapping("/{answerId}")
    public ResponseEntity getAnswer(@PathVariable("answerId") Long id) {

        AnswerAccountResDto account = new AnswerAccountResDto();
        account.setId(1L);
        account.setEmail("account@gmail.com");
        account.setPath("path");
        account.setNickname("nickname");
        AnswerResDto answerResDto = new AnswerResDto(id, "title", "content",10,account);
        answerResDto.setCreatedAt(LocalDateTime.now());
        answerResDto.setModifiedAt(LocalDateTime.now());

        return new ResponseEntity<>(answerResDto, HttpStatus.OK);
    }


    @GetMapping
    public PageDto getAnswers(Pageable pageable) {
        List<AnswerResDto> list = new ArrayList<>();

        for(int i =1 ; i <=10 ; i++) {
            AnswerAccountResDto account = new AnswerAccountResDto();
            account.setId(100L+i);
            account.setEmail("mock"+i+"@gmail.com");
            account.setPath("path"+i);
            account.setNickname("nick"+i);
            AnswerResDto answerResDto = new AnswerResDto(0L+i, "title"+i, "contents"+i, i, account);
            answerResDto.setCreatedAt(LocalDateTime.now());
            answerResDto.setModifiedAt(LocalDateTime.now());

            list.add(answerResDto);
        }
        Page<AnswerResDto> page = new PageImpl<>(list, pageable, 10);

        return new PageDto<>(page);
    }


    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("answerId") Long id) {

        return new ResponseEntity<>(String.format("DELETE request accepted. ID: %d",id), HttpStatus.OK);  /// 추후 NO_CONTENT로 변경해야함
    }
}