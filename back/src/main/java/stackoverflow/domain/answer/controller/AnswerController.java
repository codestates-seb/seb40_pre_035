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

    // Mock API
    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerPostDto answerPostDto) {
        Answer answer = new Answer();
        answer.setAnswerId(answerPostDto.getAnswerId());
        answer.setTitle(answerPostDto.getTitle());
        answer.setContent(answerPostDto.getContent());
        Answer response = answerService.createAnswer(answer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Mock API
    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") Long answerId,
                                      @RequestBody AnswerPatchDto answerPatchDto) {
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setTitle(answerPatchDto.getTitle());
        answer.setContent(answerPatchDto.getContent());

        Answer response = answerService.updateAnswer(answer);
        AnswerResponseDto mockResponse = new AnswerResponseDto(response);

        return new ResponseEntity<>(mockResponse, HttpStatus.OK);
    }

    // Mock API
    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") Long answerId) {
        Answer response = answerService.findAnswer(answerId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mock API
    @GetMapping
    public ResponseEntity getAnswers() {
        List<Answer> response = answerService.findAnswers();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mock API
    @DeleteMapping("/{answer-id}")
    public String deleteAnswer(@PathVariable("answer-id") Long answerId) {
        return "성공적으로 삭제되었습니다.";
    }



    // 실제 API
//    @PostMapping
//    public ResponseEntity postAnswer(@RequestBody AnswerPostDto answerPostDto) {
//        Answer answer = new Answer();
//        answer.setTitle(answerPostDto.getTitle());
//        answer.setContent(answerPostDto.getContent());
//        Answer response = answerService.createAnswer(answer);
//
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

//    @PatchMapping("/{answer-id}")
//    public ResponseEntity patchAnswer(@PathVariable("answer-id") Long answerId,
//                                      @RequestBody AnswerPatchDto answerPatchDto) {
//        // PatchDTO받아서 => Answer로 변환시키고 => service의 updateAnswer에 보내고 => updateAnswer한 Answer를 받아서 => ResponseDTO로
//        Answer answer = new Answer();
//        answer.setAnswerId(answerId);
//        answer.setTitle(answerPatchDto.getTitle());
//        answer.setContent(answerPatchDto.getContent());
//        Answer reponse = answerService.updateAnswer(answer);
//
//        return new ResponseEntity<>(reponse, HttpStatus.OK);
//    }

//    @GetMapping("/{answer-id}")
//    public ResponseEntity getAnswer(@PathVariable("answer-id") Long answerId) {
//        Answer response = answerService.findAnswer(answerId);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


//    @GetMapping
//    public ResponseEntity getAnswers() {
//        List<Answer> response = answerService.findAnswers();
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @DeleteMapping("/{answer-id}")
//    public ResponseEntity deleteAnswer(@PathVariable("answer-id") Long answerId) {
//        answerService.deleteAnswer(answerId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
