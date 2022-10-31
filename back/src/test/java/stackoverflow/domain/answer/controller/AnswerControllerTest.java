package stackoverflow.domain.answer.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Disabled;
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
import stackoverflow.domain.answer.dto.AddAnswerVoteReqDto;
import stackoverflow.domain.answer.dto.AnswerReqDto;
import stackoverflow.global.common.enums.VoteState;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
        Long answerId = 1L;

        //when
        ResultActions actions = mockMvc.perform(
                get("/answers/{answerId}", answerId)
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
                                        fieldWithPath("account.profile").type(JsonFieldType.STRING).description("Answer 생성계정 프로필 이미지 경로"),
                                        fieldWithPath("account.nickname").type(JsonFieldType.STRING).description("Answer 생성계정 별칭")
                                )
                        )
                ));
    }


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answers 조회_성공")
    public void getAnswers_Success_Test() throws Exception {
        //given
        Long answerId = 1L;

        //when
        ResultActions actions = mockMvc.perform(
                get("/answers")
                        .param("page", "1")
                        .param("size","10")
                        .param("sort", "id,desc")
        );

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("getAnswers",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호(default = 1"),
                                parameterWithName("size").description("페이징 size(default = 10)"),
                                parameterWithName("sort").description("정렬 조건(default = id, asc")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Answer 목록"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Answer 생성일자"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Answer 수정일자"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Answer 식별자"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Answer 내용"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Answer Vote"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Answer 생성계정"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Answer 생성계정 식별자"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Answer 생성계정 email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Answer 생성계정 프로필 이미지 경로"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Answer 생성계정 별칭"),

                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("전체 Answer 개수"),
                                        fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("첫 페이지 여부"),
                                        fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                        fieldWithPath("sorted").type(JsonFieldType.BOOLEAN).description("정렬 여부"),
                                        fieldWithPath("size").type(JsonFieldType.NUMBER).description("페이징 size"),
                                        fieldWithPath("pageNumber").type(JsonFieldType.NUMBER).description("페이지 번호(0부터 시작)"),
                                        fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("페이징된 Question 개수")
                                )
                        )
                ));

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

    @Test
    @DisplayName("AnswerVote_생성_성공")
    public void addAnswerVote_Success_Test() throws Exception {
        //give
        String jwt = "AccessToken_Value";
        VoteState state = VoteState.UP;
        Long answerId = 1L;

        AddAnswerVoteReqDto addAnswerVoteReqDto = new AddAnswerVoteReqDto();
        addAnswerVoteReqDto.setState(state);

        String body = gson.toJson(addAnswerVoteReqDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/answers/answerVote/{answerId}", answerId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .content(body)
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andDo(document("addAnswerVote",
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
                                                fieldWithPath("state").type(JsonFieldType.STRING).description("Vote 상태")
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

    @Test @Disabled
    @DisplayName("AccountAnswers_조회_성공")
    public void getAccountAnswers_Success_Test() throws Exception {
        //given
        Long accountId = 1L;

        ResultActions actions = mockMvc.perform(
                get("/answers/account/{accountId}", accountId)
                        .param("page", "1")
                        .param("size","10")
                        .param("sort", "id,desc")
        );

        // then

        actions
                .andExpect(status().isOk())
                .andDo(document("getAccountAnswers",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("accountId").description("Account 식별자")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호(default = 1"),
                                parameterWithName("size").description("페이징 size(default = 10)"),
                                parameterWithName("sort").description("정렬 조건(default = id, asc")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Answer 목록"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Answer 생성일자"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Answer 수정일자"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Answer 식별자"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Answer 내용"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Answer Vote"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Answer 생성계정"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Answer 생성계정 식별자"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Answer 생성계정 email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Answer 생성계정 프로필 이미지 경로"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Answer 생성계정 별칭"),

                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("전체 Answer 개수"),
                                        fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("첫 페이지 여부"),
                                        fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                        fieldWithPath("sorted").type(JsonFieldType.BOOLEAN).description("정렬 여부"),
                                        fieldWithPath("size").type(JsonFieldType.NUMBER).description("페이징 size"),
                                        fieldWithPath("pageNumber").type(JsonFieldType.NUMBER).description("페이지 번호(0부터 시작)"),
                                        fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("페이징된 Question 개수")
                                )
                        )
                ));
    }

}
