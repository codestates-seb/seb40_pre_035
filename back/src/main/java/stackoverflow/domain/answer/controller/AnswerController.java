package stackoverflow.domain.answer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.answer.dto.AnswerPatchDto;
import stackoverflow.domain.answer.dto.AnswerPostDto;
import stackoverflow.domain.answer.dto.AnswerResponseDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.service.AnswerService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/answer")
@RestController
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerPostDto answerPostDto) {
        Answer answer = answerService.createAnswer(answerPostDto.setAnswer());
        AnswerResponseDto response = new AnswerResponseDto(answer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") Long answerId,
                                      @RequestBody AnswerPatchDto answerPatchDto) {
        Answer mockAnswer = new Answer();
        mockAnswer.setAnswerId(answerId);
        mockAnswer.setTitle(answerPatchDto.getTitle());
        mockAnswer.setContent(answerPatchDto.getContent());

        AnswerResponseDto mockResponse = new AnswerResponseDto(mockAnswer);

        return new ResponseEntity<>(mockResponse, HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") Long answerId) {
//        Answer answer = answerService.findAnswer(answerId);
//        AnswerResponseDto response = new AnswerResponseDto(answer);

        Answer response = new Answer();
        response.setAnswerId(answerId);
        response.setTitle("mockTitle");
        response.setContent("mockContent");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers() {
        List<AnswerResponseDto> response = new ArrayList<>();

        AnswerResponseDto answerRes1 = new AnswerResponseDto(1L, "title1","contents1");
        AnswerResponseDto answerRes2 = new AnswerResponseDto(2L, "title2","contents2");
        AnswerResponseDto answerRes3 = new AnswerResponseDto(3L, "title3","contents3");
        AnswerResponseDto answerRes4 = new AnswerResponseDto(4L, "title4","contents4");
        AnswerResponseDto answerRes5 = new AnswerResponseDto(5L, "title5","contents5");
        response.add(answerRes1);
        response.add(answerRes2);
        response.add(answerRes3);
        response.add(answerRes4);
        response.add(answerRes5);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @DeleteMapping("/{answer-id}")
//    public ResponseEntity deleteAnswer(@PathVariable("answer-id") Long answerId) {
//        answerService.deleteAnswer(answerId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


    @DeleteMapping("/{answer-id}")
    public String deleteAnswer(@PathVariable("answer-id") Long answerId) {
        return "성공적으로 삭제되었습니다.";
    }
}
