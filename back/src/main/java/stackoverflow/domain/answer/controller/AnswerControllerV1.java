package stackoverflow.domain.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.answer.dto.AnswerReqDto;
import stackoverflow.domain.answer.dto.AnswerResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.service.AnswerService;
import stackoverflow.global.common.dto.PageDto;

import javax.validation.constraints.Positive;

@RequiredArgsConstructor
@RequestMapping("/answer")
//@RestController
public class AnswerControllerV1 {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerReqDto answerReqDto) {
        Answer answer = answerReqDto.toAnswer(answerReqDto);
        Answer response = answerService.createAnswer(answer);

        return new ResponseEntity<>(new AnswerResDto(response), HttpStatus.CREATED);
    }


    @PatchMapping("/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable("answerId") Long answerId,
                                      @RequestBody AnswerReqDto answerReqDto) {
        Answer answer = answerReqDto.setAnswer(answerId, answerReqDto);
        Answer reponse = answerService.updateAnswer(answer);

        return new ResponseEntity<>(new AnswerResDto(reponse), HttpStatus.OK);
    }


    @GetMapping("/{answerId}")
    public ResponseEntity getAnswer(@PathVariable("answerId") Long id) {
        Answer response = answerService.findAnswer(id);
        AnswerResDto responseDto = new AnswerResDto(response);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @GetMapping
    public PageDto getAnswers(@Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Answer> answerPage = answerService.findAnswers(page, size);
        PageDto pageDto = new PageDto<>(answerPage);

        return pageDto;

//        List<AnswerResponseDto> responseDto = new ArrayList<>(
//                response.stream()
//                        .map(Response -> new AnswerResponseDto(Response))
//                        .collect(Collectors.toList()));
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("answerId") Long id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
