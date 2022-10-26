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
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.service.AnswerService;
import stackoverflow.global.common.dto.PageDto;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/answer")
@RestController
public class AnswerController {
    private final AnswerService answerService;


    @PostMapping
    public String postAnswer(@RequestBody AnswerReqDto answerReqDto) {

        return "POST request accepted";
    }


    @PatchMapping("/{answerId}")
    public String patchAnswer(@PathVariable("answerId") Long answerId,
                                      @RequestBody AnswerReqDto answerReqDto) {
        return "PATCH request accepted";
    }


    @GetMapping("/{answerId}")
    public String getAnswer(@PathVariable("answerId") Long id) {
        return "GET request accepted";
    }


    @GetMapping
    public PageDto getAnswers(Pageable pageable) {
        List<AnswerResDto> list = new ArrayList<>();

        for(int i =1 ; i <=20 ; i++) {
            AnswerAccountResDto account = new AnswerAccountResDto();
            account.setId(0L+i);
            account.setEmail("mock"+i+"@gmail.com");
            account.setPath("path"+i);
            account.setNickname("nick"+i);

            AnswerResDto answerResDto = new AnswerResDto(0L+i, "title"+i, "contents"+i, i, account);

            list.add(answerResDto);
        }

        Page<AnswerResDto> page = new PageImpl<>(list, pageable, 20);

        return new PageDto<>(page);
    }


    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("answerId") Long id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
