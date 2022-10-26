package stackoverflow.domain.answer.dto;

import lombok.*;
import stackoverflow.domain.answer.entity.Answer;


@Getter
@Setter
public class AnswerReqDto {

    private String title;

    private String content;

    private int totalVote;

    public Answer toAnswer(AnswerReqDto answerReqDto) {
        Answer answer = new Answer();
        answer.setTitle(answerReqDto.getTitle());
        answer.setContent(answerReqDto.getContent());
        answer.setTotalVote(answerReqDto.getTotalVote());

        return answer;
    }

    public Answer setAnswer(Long id, AnswerReqDto answerReqDto) {
        Answer answer = new Answer();
        answer.setId(id);
        answer.setTitle(answerReqDto.getTitle());
        answer.setContent(answerReqDto.getContent());
        answer.setTotalVote(answerReqDto.getTotalVote());

        return answer;
    }
}
