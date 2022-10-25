package stackoverflow.domain.answer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.answer.dto.AnswerPostDto;
import stackoverflow.domain.answer.dto.AnswerResponseDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.service.AnswerService;

@RequestMapping("/answer")
@RestController
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerPostDto answerPostDto) {
        Answer answer = answerService.createAnswer(answerPostDto.setAnswer());
        AnswerResponseDto response = new AnswerResponseDto(answer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer() {
        return null;
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer() {
        return null;
    }

    @GetMapping
    public ResponseEntity getAnswers() {
        return null;
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer() {
        return null;
    }
}
