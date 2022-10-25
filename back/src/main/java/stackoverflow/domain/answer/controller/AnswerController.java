package stackoverflow.domain.answer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.answer.dto.AnswerRequestDto;
import stackoverflow.domain.answer.dto.AnswerResponseDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.service.AnswerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/answer")
@RestController
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // 실제 API
    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerRequestDto answerPostDto) {
        Answer answer = new Answer();
        answer.setAnswerId(answerPostDto.getAnswerId());
        answer.setTitle(answerPostDto.getTitle());
        answer.setContent(answerPostDto.getContent());
        Answer response = answerService.createAnswer(answer);
        AnswerResponseDto responseDto = new AnswerResponseDto(response);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") Long answerId,
                                      @RequestBody AnswerRequestDto answerRequestDto) {
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setTitle(answerRequestDto.getTitle());
        answer.setContent(answerRequestDto.getContent());
        Answer reponse = answerService.updateAnswer(answer);
        AnswerResponseDto responseDto = new AnswerResponseDto(reponse);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") Long answerId) {
        Answer response = answerService.findAnswer(answerId);
        AnswerResponseDto responseDto = new AnswerResponseDto(response);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity getAnswers() {
        List<Answer> response = answerService.findAnswers();
        List<AnswerResponseDto> responseDto = new ArrayList<>(
                response.stream()
                        .map(Response -> new AnswerResponseDto(Response))
                        .collect(Collectors.toList()));

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") Long answerId) {
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
