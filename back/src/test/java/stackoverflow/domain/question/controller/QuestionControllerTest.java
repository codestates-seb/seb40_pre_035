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
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.domain.question.dto.QuestionVoteReqDto;
import stackoverflow.domain.question.dto.AddQuestionReqDto;
import stackoverflow.global.common.enums.VoteState;
import stackoverflow.global.security.auth.jwt.JwtTokenizer;

import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stackoverflow.util.ApiDocumentUtils.getRequestPreProcessor;
import static stackoverflow.util.ApiDocumentUtils.getResponsePreProcessor;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtTokenizer jwtTokenizer;

    @Test
    @DisplayName("Question ??????_??????")
    void createQuestion_Success_Test() throws Exception {

        //given
        Account account = accountRepository.findById(1L).get();
        String accessToken = jwtTokenizer.delegateAccessToken(account);

        String title = "testQuestionTitle";
        String content = "testQuestionContenttestQuestionContenttestQuestionContent";
        String jwt = "Bearer " + accessToken;

        AddQuestionReqDto addQuestionReqDto = new AddQuestionReqDto();
        addQuestionReqDto.setTitle(title);
        addQuestionReqDto.setContent(content);

        String body = gson.toJson(addQuestionReqDto);

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
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("??????")

                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("Question ?????????")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question ??????_??????")
    void modifyQuestion_Success_Test() throws Exception {

        //given
        Account account = accountRepository.findById(1L).get();
        String accessToken = jwtTokenizer.delegateAccessToken(account);

        long questionId = 101L;
        String title = "testQuestionTitleModify";
        String content = "testQuestionContenttestQuestionContenttestQuestionContentModify";
        String jwt = "Bearer " + accessToken;

        AddQuestionReqDto addQuestionReqDto = new AddQuestionReqDto();
        addQuestionReqDto.setTitle(title);
        addQuestionReqDto.setContent(content);

        String body = gson.toJson(addQuestionReqDto);

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
                                parameterWithName("questionId").description("Question ?????????")
                        ),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("??????")

                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("Api ?????? ?????????")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question ??????_??????")
    void deleteQuestion() throws Exception {

        //given
        Account account = accountRepository.findById(1L).get();
        String accessToken = jwtTokenizer.delegateAccessToken(account);

        long questionId = 101L;
        String jwt = "Bearer " + accessToken;

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
                                parameterWithName("questionId").description("Question ?????????")
                        ),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("Api ?????? ?????????")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("?????? Question ??????_??????")
    void getQuestion_Success_Test() throws Exception {

        //given
        long questionId = 101L;

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions/{questionId}", questionId)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalVote").value(-1))
                .andDo(document(
                        "getQuestion",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("questionId").description("Question ?????????")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("Question ?????????"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("totalVote").type(JsonFieldType.NUMBER).description("Question Vote"),
                                        fieldWithPath("account").type(JsonFieldType.OBJECT).description("Question ?????????"),
                                        fieldWithPath("account.id").type(JsonFieldType.NUMBER).description("Question ????????? ?????????"),
                                        fieldWithPath("account.email").type(JsonFieldType.STRING).description("Question ????????? email"),
                                        fieldWithPath("account.profile").type(JsonFieldType.STRING).description("Question ????????? ????????? ????????? ??????"),
                                        fieldWithPath("account.nickname").type(JsonFieldType.STRING).description("Question ????????? ??????")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Questions ??????_??????")
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
                                parameterWithName("page").description("????????? ??????(default = 1)"),
                                parameterWithName("size").description("????????? size(default = 10)"),
                                parameterWithName("sort").description("?????? ??????(default = asc)"),
                                parameterWithName("keyword").description("?????????(required=false)")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Question ??????"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Question ?????????"),
                                        fieldWithPath("content[].title").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Question Vote"),
                                        fieldWithPath("content[].answerCount").type(JsonFieldType.NUMBER).description("Question??? Answer ??????"),
                                        fieldWithPath("content[].selectedAnswer").type(JsonFieldType.BOOLEAN).description("Question??? Answer ?????? ??????"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Question ?????????"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Question ????????? ?????????"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Question ????????? email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Question ????????? ????????? ????????? ??????"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Question ????????? ??????"),
                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("?????? Question ??????"),
                                        fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("??? ????????? ??????"),
                                        fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("????????? ????????? ??????"),
                                        fieldWithPath("sorted").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("size").type(JsonFieldType.NUMBER).description("????????? size"),
                                        fieldWithPath("pageNumber").type(JsonFieldType.NUMBER).description("????????? ??????(0?????? ??????)"),
                                        fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("???????????? Question ??????")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("?????? Account??? Questions ??????_??????")
    void getQuestionsAccount_Success_Test() throws Exception {

        //given
        Long accountId = 1L;

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions/account/{accountId}", accountId)
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
                        pathParameters(
                                parameterWithName("accountId").description("Account ?????????")
                        ),
                        requestParameters(
                                parameterWithName("page").description("????????? ??????(default = 1)"),
                                parameterWithName("size").description("????????? size(default = 10)"),
                                parameterWithName("sort").description("?????? ??????(default = asc)")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Question ??????"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Question ?????????"),
                                        fieldWithPath("content[].title").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Question Vote"),
                                        fieldWithPath("content[].answerCount").type(JsonFieldType.NUMBER).description("Question??? Answer ??????"),
                                        fieldWithPath("content[].selectedAnswer").type(JsonFieldType.BOOLEAN).description("Question??? Answer ?????? ??????"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Question ?????????"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Question ????????? ?????????"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Question ????????? email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Question ????????? ????????? ????????? ??????"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Question ????????? ??????"),
                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("?????? Question ??????"),
                                        fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("??? ????????? ??????"),
                                        fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("????????? ????????? ??????"),
                                        fieldWithPath("sorted").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("size").type(JsonFieldType.NUMBER).description("????????? size"),
                                        fieldWithPath("pageNumber").type(JsonFieldType.NUMBER).description("????????? ??????(0?????? ??????)"),
                                        fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("???????????? Question ??????")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Questions ??????_UnAnswered_??????")
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
                                parameterWithName("page").description("????????? ??????(default = 1)"),
                                parameterWithName("size").description("????????? size(default = 10)"),
                                parameterWithName("sort").description("?????? ??????(default = asc)"),
                                parameterWithName("keyword").description("?????????(required=false)")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Question ??????"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Question ?????????"),
                                        fieldWithPath("content[].title").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Question Vote"),
                                        fieldWithPath("content[].answerCount").type(JsonFieldType.NUMBER).description("Question??? Answer ??????"),
                                        fieldWithPath("content[].selectedAnswer").type(JsonFieldType.BOOLEAN).description("Question??? Answer ?????? ??????"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Question ?????????"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Question ????????? ?????????"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Question ????????? email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Question ????????? ????????? ????????? ??????"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Question ????????? ??????"),
                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("?????? Question ??????"),
                                        fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("??? ????????? ??????"),
                                        fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("????????? ????????? ??????"),
                                        fieldWithPath("sorted").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("size").type(JsonFieldType.NUMBER).description("????????? size"),
                                        fieldWithPath("pageNumber").type(JsonFieldType.NUMBER).description("????????? ??????(0?????? ??????)"),
                                        fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("???????????? Question ??????")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Questions ??????_totalVote_??????")
    void totalVoteQuestionList_Success_Test() throws Exception {

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions/totalVote")
                        .param("page", "1")
                        .param("size", "10")
                        .param("keyword", "test")
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "getQuestionsTotalVote",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("page").description("????????? ??????(default = 1)"),
                                parameterWithName("size").description("????????? size(default = 10)"),
                                parameterWithName("keyword").description("?????????(required=false)")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Question ??????"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Question ????????????"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Question ?????????"),
                                        fieldWithPath("content[].title").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Question ??????"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Question Vote"),
                                        fieldWithPath("content[].answerCount").type(JsonFieldType.NUMBER).description("Question??? Answer ??????"),
                                        fieldWithPath("content[].selectedAnswer").type(JsonFieldType.BOOLEAN).description("Question??? Answer ?????? ??????"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Question ?????????"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Question ????????? ?????????"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Question ????????? email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Question ????????? ????????? ????????? ??????"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Question ????????? ??????"),
                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("?????? Question ??????"),
                                        fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("??? ????????? ??????"),
                                        fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("????????? ????????? ??????"),
                                        fieldWithPath("sorted").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("size").type(JsonFieldType.NUMBER).description("????????? size"),
                                        fieldWithPath("pageNumber").type(JsonFieldType.NUMBER).description("????????? ??????(0?????? ??????)"),
                                        fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("???????????? Question ??????")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("QuestionVote ??????_??????")
    void questionVoteAdd_Success_Test() throws Exception {

        //given
        Account account = accountRepository.findById(1L).get();
        String accessToken = jwtTokenizer.delegateAccessToken(account);

        long questionId = 107L;
        String jwt = "Bearer " + accessToken;
        QuestionVoteReqDto questionVoteReqDto = new QuestionVoteReqDto();
        questionVoteReqDto.setState(VoteState.UP);
        String body = gson.toJson(questionVoteReqDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/questions/questionVote/{questionId}", questionId)
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
                                parameterWithName("questionId").description("Question ?????????")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("state").type(JsonFieldType.STRING).description("Vote ??????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("Api ?????? ?????????")
                                )
                        )
                ));
    }
}