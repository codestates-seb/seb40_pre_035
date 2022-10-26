package stackoverflow.domain.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
import stackoverflow.domain.account.dto.AnswerAccountResDto;
>>>>>>> feat/#4_1
import stackoverflow.domain.answer.dto.AnswerReqDto;
import stackoverflow.domain.answer.dto.AnswerResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.service.AnswerService;
import stackoverflow.global.common.dto.PageDto;
<<<<<<< HEAD
import javax.validation.constraints.Positive;
=======

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
>>>>>>> feat/#4_1

@RequiredArgsConstructor
@RequestMapping("/answer")
@RestController
public class AnswerController {
    private final AnswerService answerService;


    @PostMapping
<<<<<<< HEAD
    public ResponseEntity postAnswer(@RequestBody AnswerReqDto answerReqDto) {
        Answer answer = answerReqDto.toAnswer(answerReqDto);
        Answer response = answerService.createAnswer(answer);

        return new ResponseEntity<>(new AnswerResDto(response), HttpStatus.CREATED);
=======
    public String postAnswer(@RequestBody AnswerReqDto answerReqDto) {
        return "POST request accepted";
>>>>>>> feat/#4_1
    }


    @PatchMapping("/{answerId}")
<<<<<<< HEAD
    public ResponseEntity patchAnswer(@PathVariable("answerId") Long answerId,
                                      @RequestBody AnswerReqDto answerReqDto) {
        Answer answer = answerReqDto.setAnswer(answerId, answerReqDto);
        Answer reponse = answerService.updateAnswer(answer);

        return new ResponseEntity<>(new AnswerResDto(reponse), HttpStatus.OK);
=======
    public String patchAnswer(@PathVariable("answerId") Long answerId,
                                      @RequestBody AnswerReqDto answerReqDto) {
        return "PATCH request accepted";
>>>>>>> feat/#4_1
    }


    @GetMapping("/{answerId}")
<<<<<<< HEAD
    public ResponseEntity getAnswer(@PathVariable("answerId") Long id) {
        Answer response = answerService.findAnswer(id);
        AnswerResDto responseDto = new AnswerResDto(response);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
=======
    public String getAnswer(@PathVariable("answerId") Long id) {
        return "GET request accepted";
>>>>>>> feat/#4_1
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
