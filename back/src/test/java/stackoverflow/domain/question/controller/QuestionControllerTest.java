package stackoverflow.domain.question.controller;

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
import stackoverflow.domain.question.dto.AddQuestionVoteReqDto;
import stackoverflow.domain.question.dto.QuestionReqDto;
import stackoverflow.global.common.enums.VoteState;

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
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("Question 생성_성공")
    void createQuestion_Success_Test() throws Exception {

        //given
        String title = "testQuestionTitle";
        String content = "testQuestionContenttestQuestionContenttestQuestionContent";
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";

        QuestionReqDto questionReqDto = new QuestionReqDto();
        questionReqDto.setTitle(title);
        questionReqDto.setContent(content);

        String body = gson.toJson(questionReqDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .content(body)
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andDo(document(
                        "createQuestion",
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
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용")

                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("Api 성공 메시지")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question 수정_성공")
    void modifyQuestion_Success_Test() throws Exception {

        //given
        long questionId = 6L;
        String title = "testQuestionTitleModify";
        String content = "testQuestionContenttestQuestionContenttestQuestionContentModify";
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";

        QuestionReqDto questionReqDto = new QuestionReqDto();
        questionReqDto.setTitle(title);
        questionReqDto.setContent(content);

        String body = gson.toJson(questionReqDto);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/questions/{questionId}", questionId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .content(body)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "modifyQuestion",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("questionId").description("Question 식별자")
                        ),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용")

                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("Api 성공 메시지")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question 삭제_성공")
    void deleteQuestion() throws Exception {

        //given
        long questionId = 1L;
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";

        //when
        ResultActions actions = mockMvc.perform(
                delete("/questions/{questionId}", questionId)
                        .header("Authorization", jwt)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "deleteQuestion",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("questionId").description("Question 식별자")
                        ),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("Api 성공 메시지")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("단일 Question 조회_성공")
    void getQuestion_Success_Test() throws Exception {

        //given
        long questionId = 1L;

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions/{questionId}", questionId)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "getQuestion",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("questionId").description("Question 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("Question 생성일자"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("Question 수정일자"),
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("Question 식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("Question 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("Question 내용"),
                                        fieldWithPath("totalVote").type(JsonFieldType.NUMBER).description("Question Vote"),
                                        fieldWithPath("account").type(JsonFieldType.OBJECT).description("Question 생성자"),
                                        fieldWithPath("account.id").type(JsonFieldType.NUMBER).description("Question 생성자 식별자"),
                                        fieldWithPath("account.email").type(JsonFieldType.STRING).description("Question 생성자 email"),
                                        fieldWithPath("account.profile").type(JsonFieldType.STRING).description("Question 생성자 프로필 이미지 경로"),
                                        fieldWithPath("account.nickname").type(JsonFieldType.STRING).description("Question 생성자 별칭")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Questions 조회_성공")
    void getQuestions_Success_Test() throws Exception {

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions")
                        .param("page", "1")
                        .param("size", "10")
                        .param("sort", "id,desc")
                        .param("keyword", "test")
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "getQuestions",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호(default = 1)"),
                                parameterWithName("size").description("페이징 size(default = 10)"),
                                parameterWithName("sort").description("정렬 조건(default = asc)"),
                                parameterWithName("keyword").description("검색어(required=false)")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Question 목록"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Question 생성일자"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Question 수정일자"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Question 식별자"),
                                        fieldWithPath("content[].title").type(JsonFieldType.STRING).description("Question 제목"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Question 내용"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Question Vote"),
                                        fieldWithPath("content[].answerCount").type(JsonFieldType.NUMBER).description("Question의 Answer 개수"),
                                        fieldWithPath("content[].selectedAnswer").type(JsonFieldType.BOOLEAN).description("Question의 Answer 채택 여부"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Question 생성자"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Question 생성자 식별자"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Question 생성자 email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Question 생성자 프로필 이미지 경로"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Question 생성자 별칭"),
                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("전체 Question 개수"),
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

    @Test
    @DisplayName("특정 Account의 Questions 검색_성공")
    void getQuestionsAccount_Success_Test() throws Exception {

        //given
        Long accountId = 1L;
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";


        //when
        ResultActions actions = mockMvc.perform(
                get("/questions/account/{accountId}", accountId)
                        .header("Authorization", jwt)
                        .param("page", "1")
                        .param("size", "10")
                        .param("sort", "id,desc")
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "getQuestionsAccount",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        pathParameters(
                                parameterWithName("accountId").description("Account 식별자")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호(default = 1)"),
                                parameterWithName("size").description("페이징 size(default = 10)"),
                                parameterWithName("sort").description("정렬 조건(default = asc)")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Question 목록"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Question 생성일자"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Question 수정일자"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Question 식별자"),
                                        fieldWithPath("content[].title").type(JsonFieldType.STRING).description("Question 제목"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Question 내용"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Question Vote"),
                                        fieldWithPath("content[].answerCount").type(JsonFieldType.NUMBER).description("Question의 Answer 개수"),
                                        fieldWithPath("content[].selectedAnswer").type(JsonFieldType.BOOLEAN).description("Question의 Answer 채택 여부"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Question 생성자"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Question 생성자 식별자"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Question 생성자 email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Question 생성자 프로필 이미지 경로"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Question 생성자 별칭"),
                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("전체 Question 개수"),
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

    @Test
    @DisplayName("Questions 조회_UnAnswered_성공")
    void getQuestionsUnAnswered_Success_Test() throws Exception {

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions/unAnswered")
                        .param("page", "1")
                        .param("size", "10")
                        .param("sort", "id,desc")
                        .param("keyword", "test")
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "getQuestionsUnAnswered",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호(default = 1)"),
                                parameterWithName("size").description("페이징 size(default = 10)"),
                                parameterWithName("sort").description("정렬 조건(default = asc)"),
                                parameterWithName("keyword").description("검색어(required=false)")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Question 목록"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Question 생성일자"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Question 수정일자"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Question 식별자"),
                                        fieldWithPath("content[].title").type(JsonFieldType.STRING).description("Question 제목"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Question 내용"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Question Vote"),
                                        fieldWithPath("content[].answerCount").type(JsonFieldType.NUMBER).description("Question의 Answer 개수"),
                                        fieldWithPath("content[].selectedAnswer").type(JsonFieldType.BOOLEAN).description("Question의 Answer 채택 여부"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Question 생성자"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Question 생성자 식별자"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Question 생성자 email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Question 생성자 프로필 이미지 경로"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Question 생성자 별칭"),
                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("전체 Question 개수"),
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

    @Test
    @DisplayName("QuestionVote 생성_성공")
    void questionVoteAdd_Success_Test() throws Exception {

        //given
        Long questionId = 1L;
        AddQuestionVoteReqDto addQuestionVoteReqDto = new AddQuestionVoteReqDto();
        addQuestionVoteReqDto.setState(VoteState.UP);
        String body = gson.toJson(addQuestionVoteReqDto);
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";

        //when
        ResultActions actions = mockMvc.perform(
                post("/questionVote/{questionId}", questionId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .content(body));

        actions
                .andExpect(status().isCreated())
                .andDo(document(
                        "addQuestionVote",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        pathParameters(
                                parameterWithName("questionId").description("Question 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("state").type(JsonFieldType.STRING).description("Vote 상태")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("Api 성공 메시지")
                                )
                        )
                ));
    }
}