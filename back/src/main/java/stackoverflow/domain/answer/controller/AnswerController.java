package stackoverflow.domain.answer.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.answer.dto.AnswerPostDto;
import stackoverflow.domain.answer.dto.AnswerResponseDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.service.AnswerService;

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
