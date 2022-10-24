package stackoverflow.AnswerDomain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.AnswerDomain.dto.AnswerPostDto;
import stackoverflow.AnswerDomain.dto.AnswerResponseDto;
import stackoverflow.AnswerDomain.entity.Answer;
import stackoverflow.AnswerDomain.service.AnswerService;

@RequestMapping("/answer")
@RestController
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public RequestEntity postAnswer(@RequestBody AnswerPostDto answerPostDto) {
        Answer answer = answerService.createAnswer(answerPostDto.setAnswer());
        AnswerResponseDto response = new AnswerResponseDto(answer);

        // 리턴을 위한 SingleResponseDto미완성
//        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
        return null;
    }

    @PatchMapping
    public RequestEntity patchAnswer() {
        return null;
    }

    @GetMapping
    public RequestEntity getAnswer() {
        return null;
    }

    @GetMapping
    public RequestEntity getAnswers() {
        return null;
    }

    @DeleteMapping
    public RequestEntity deleteAnswer() {
        return null;
    }
}
