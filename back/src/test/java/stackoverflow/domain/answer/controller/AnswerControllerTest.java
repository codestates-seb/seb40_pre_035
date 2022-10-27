package stackoverflow.domain.answer.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import stackoverflow.domain.answer.dto.AnswerReqDto;

import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stackoverflow.util.ApiDocumentUtils.getRequestPreProcessor;
import static stackoverflow.util.ApiDocumentUtils.getResponsePreProcessor;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("Answer 생성_성공")
    public void createAnswer_Success_Test() throws Exception {
        //given
        String jwt = "AccessToken_Value";
        String title = "testAnswerTitle";
        String content = "testAnswerContent";
        int totalVote = 0;

        AnswerReqDto answerReqDto = new AnswerReqDto();
        answerReqDto.setTitle(title);
        answerReqDto.setContent(content);
        answerReqDto.setTotalVote(totalVote);

        String body = gson.toJson(answerReqDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/answers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .content(body)
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andDo(document("createAnswer",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestHeaders(
                                        List.of(
                                                headerWithName("Authorization").description("JWT")
                                        )
                                ),
                                requestFields(
                                        List.of(
                                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                                fieldWithPath("totalVote").type(JsonFieldType.NUMBER).description("총 투표")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(JsonFieldType.STRING).description("Api 성공 메시지")
                                        )
                                )
                        )
                );
    }


    @Test
    @DisplayName("Answer 수정_성공")
    public void modifyQuestion_Success_Test() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("Answer 삭제_성공")
    public void deleteAnswer_Success_Test() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("Answer 삭제_성공")
    public void getAnswer_Success_Test() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("Answer 삭제_성공")
    public void getAnswers_Success_Test() throws Exception {
        //given

        //when

        //then

    }

}
