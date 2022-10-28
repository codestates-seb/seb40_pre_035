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

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answer 생성_성공")
    public void createAnswer_Success_Test() throws Exception {
        //given
        String jwt = "AccessToken_Value";
        String content = "testAnswerContent";
        int totalVote = 0;

        AnswerReqDto answerReqDto = new AnswerReqDto();
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
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                                fieldWithPath("totalVote").type(JsonFieldType.NUMBER).description("총 투표")
                                        )
                                ),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.STRING).description("Api 성공 메시지")
                                        )
                                )
                        )
                );
    }


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answer 수정_성공")
    public void modifyQuestion_Success_Test() throws Exception {
        //given
        String jwt = "AccessToken_Value";

        Long answerId = 1L;
        String content = "testAnswerContent";
        int totalVote = 0;

        AnswerReqDto answerReqDto = new AnswerReqDto();
        answerReqDto.setContent(content);
        answerReqDto.setTotalVote(totalVote);

        String body = gson.toJson(answerReqDto);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/answers/{answerId}", answerId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .content(body)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document("modifyAnswer",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        parameterWithName("answerId").description("Answer 식별자")
                                ),
                                requestHeaders(
                                        List.of(
                                                headerWithName("Authorization").description("JWT")
                                        )
                                ),
                                requestFields(
                                        List.of(
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                                fieldWithPath("totalVote").type(JsonFieldType.NUMBER).description("총 투표")
                                        )
                                ),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.STRING).description("Api 성공 메시지")
                                        )
                                )
                        )
                );
    }


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answer 조회_성공")
    public void getAnswer_Success_Test() throws Exception {
        //given
        String jwt = "AccessToken_Value";
        Long answerId = 1L;
        String content = "testAnswerContent";
        int totalVote = 0;

        AnswerReqDto answerReqDto = new AnswerReqDto();
        answerReqDto.setContent(content);
        answerReqDto.setTotalVote(totalVote);

        String body = gson.toJson(answerReqDto);

        //when
        ResultActions actions = mockMvc.perform(
                get("/answers/{answerId}", answerId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .content(body)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("getAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answerId").description("Answer 식별자")
                        ),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("Answer 생성일자"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("Answer 수정일자"),
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("Answer 식별자"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("Answer 내용"),
                                        fieldWithPath("totalVote").type(JsonFieldType.NUMBER).description("Answer Vote"),
                                        fieldWithPath("account").type(JsonFieldType.OBJECT).description("Answer 생성계정"),
                                        fieldWithPath("account.id").type(JsonFieldType.NUMBER).description("Answer 생성계정 식별자"),
                                        fieldWithPath("account.email").type(JsonFieldType.STRING).description("Answer 생성계정 email"),
                                        fieldWithPath("account.path").type(JsonFieldType.STRING).description("Answer 생성계정 프로필 이미지 경로"),
                                        fieldWithPath("account.nickname").type(JsonFieldType.STRING).description("Answer 생성계정 별칭")
                                )
                        )
                ));
    }


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answers 조회_성공")
    public void getAnswers_Success_Test() throws Exception {
        // given


//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/answers")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content));
//
//        // then
//        actions
//                .andExpect(status().isOk())
//
//                .andDo(document("요청명_객체명",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                Arrays.asList(parameterWithName("answerId").description("식별자"))
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("식별자"),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
//                                )
//                        ),
//                        responseFields(
//                                Arrays.asList(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("데이터"),
//                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("식별자"),
//                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일")
//                                )
//                        )
//                ));
    }


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answer 삭제_성공")
    public void deleteAnswer_Success_Test() throws Exception {
        //given
        String jwt = "AccessToken_Value";
        Long answerId = 1L;

        //when
        ResultActions actions = mockMvc.perform(
                delete("/answers/{answerId}", answerId)
                        .header("Authorization", jwt)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document("deleteAnswer",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        parameterWithName("answerId").description("Answer 식별자")
                                ),
                                requestHeaders(
                                        List.of(
                                                headerWithName("Authorization").description("JWT")
                                        )
                                ),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.STRING).description("Api 성공 메시지")
                                        )
                                )
                        )
                );
    }
}
