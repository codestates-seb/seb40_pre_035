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
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.domain.answer.dto.AddAnswerVoteReqDto;
import stackoverflow.domain.answer.dto.AnswerReqDto;
import stackoverflow.global.common.enums.VoteState;
import stackoverflow.global.security.auth.jwt.JwtTokenizer;

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
@Transactional
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtTokenizer jwtTokenizer;


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answer ??????_??????")
    public void createAnswer_Success_Test() throws Exception {
        //given
        Account account = accountRepository.findById(1L).get();
        String accessToken = jwtTokenizer.delegateAccessToken(account);

        String jwt = "Bearer " + accessToken;
        String content = "testAnswerContent";
        Long questionId = 101L;

        AnswerReqDto answerReqDto = new AnswerReqDto();
        answerReqDto.setContent(content);
        answerReqDto.setQuestionId(questionId);

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
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("Question ?????????")
                                        )
                                ),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.STRING).description("Api ?????? ?????????")
                                        )
                                )
                        )
                );
    }


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answer ??????_??????")
    public void modifyQuestion_Success_Test() throws Exception {
        //given
        Account account = accountRepository.findById(1L).get();
        String accessToken = jwtTokenizer.delegateAccessToken(account);

        String jwt = "Bearer " + accessToken;

        Long answerId = 1001L;
        String content = "testAnswerContent";

        AnswerReqDto answerReqDto = new AnswerReqDto();
        answerReqDto.setContent(content);

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
                                        parameterWithName("answerId").description("Answer ?????????")
                                ),
                                requestHeaders(
                                        List.of(
                                                headerWithName("Authorization").description("JWT")
                                        )
                                ),
                                requestFields(
                                        List.of(
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("??????")
                                        )
                                ),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.STRING).description("Api ?????? ?????????")
                                        )
                                )
                        )
                );
    }


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answer ??????_??????")
    public void getAnswer_Success_Test() throws Exception {
        //given
        Long answerId = 1001L;

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
                                parameterWithName("answerId").description("Answer ?????????")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("Answer ????????????"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("Answer ????????????"),
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("Answer ?????????"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("Answer ??????"),
                                        fieldWithPath("totalVote").type(JsonFieldType.NUMBER).description("Answer Vote"),
                                        fieldWithPath("account").type(JsonFieldType.OBJECT).description("Answer ????????????"),
                                        fieldWithPath("account.id").type(JsonFieldType.NUMBER).description("Answer ???????????? ?????????"),
                                        fieldWithPath("account.email").type(JsonFieldType.STRING).description("Answer ???????????? email"),
                                        fieldWithPath("account.profile").type(JsonFieldType.STRING).description("Answer ???????????? ????????? ????????? ??????"),
                                        fieldWithPath("account.nickname").type(JsonFieldType.STRING).description("Answer ???????????? ??????"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("Question ?????????"),
                                        fieldWithPath("selected").type(JsonFieldType.BOOLEAN).description("?????? ??????")

                                )
                        )
                ));
    }


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answers ??????_??????")
    public void getAnswers_Success_Test() throws Exception {

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
                                parameterWithName("page").description("????????? ??????(default = 1"),
                                parameterWithName("size").description("????????? size(default = 10)"),
                                parameterWithName("sort").description("?????? ??????(default = id, desc")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Answer ??????"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Answer ????????????"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Answer ????????????"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Answer ?????????"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Answer ??????"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Answer Vote"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Answer ????????????"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Answer ???????????? ?????????"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Answer ???????????? email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Answer ???????????? ????????? ????????? ??????"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Answer ???????????? ??????"),
                                        fieldWithPath("content[].selected").type(JsonFieldType.BOOLEAN).description("?????? ??????"),

                                        fieldWithPath("content[].questionId").type(JsonFieldType.NUMBER).description("Question ?????????"),

                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("?????? Answer ??????"),
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


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answer ??????_??????")
    public void deleteAnswer_Success_Test() throws Exception {
        //given
        Account account = accountRepository.findById(1L).get();
        String accessToken = jwtTokenizer.delegateAccessToken(account);

        String jwt = "Bearer " + accessToken;
        Long answerId = 1005L;

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
                                        parameterWithName("answerId").description("Answer ?????????")
                                ),
                                requestHeaders(
                                        List.of(
                                                headerWithName("Authorization").description("JWT")
                                        )
                                ),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.STRING).description("Api ?????? ?????????")
                                        )
                                )
                        )
                );
    }

    @Test
    @DisplayName("AnswerVote_??????_??????")
    public void addAnswerVote_Success_Test() throws Exception {
        //give
        Account account = accountRepository.findById(1L).get();
        String accessToken = jwtTokenizer.delegateAccessToken(account);

        String jwt = "Bearer " + accessToken;
        Long answerId = 1005L;

        AddAnswerVoteReqDto answerVoteReqDto = new AddAnswerVoteReqDto();
        answerVoteReqDto.setState(VoteState.UP);

        String body = gson.toJson(answerVoteReqDto);

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
                                        parameterWithName("answerId").description("Answer ?????????")
                                ),
                                requestHeaders(
                                        List.of(
                                                headerWithName("Authorization").description("JWT")
                                        )
                                ),
                                requestFields(
                                        List.of(
                                                fieldWithPath("state").type(JsonFieldType.STRING).description("Vote ??????")
                                        )
                                ),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.STRING).description("Api ?????? ?????????")
                                        )
                                )
                        )
                );
    }

    @Test
    @DisplayName("QuestionAnswers_??????_??????")
    public void questionAnswersList_Success_Test() throws Exception {
        //given
        Long questionId = 101L;


        //when
        ResultActions actions = mockMvc.perform(
                get("/answers/question/{questionId}", questionId)
                        .param("page", "1")
                        .param("size","10")
                        .param("sort", "id,desc")
        );

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("QuestionAnswerList",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("questionId").description("Question ?????????")
                        ),
                        requestParameters(
                                parameterWithName("page").description("????????? ??????(default = 1"),
                                parameterWithName("size").description("????????? size(default = 10)"),
                                parameterWithName("sort").description("?????? ??????(default = id, asc)")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Answer ??????"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Answer ????????????"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Answer ????????????"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Answer ?????????"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Answer ??????"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Answer Vote"),
                                        fieldWithPath("content[].selected").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Answer ????????????"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Answer ???????????? ?????????"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Answer ???????????? email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Answer ???????????? ????????? ????????? ??????"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Answer ???????????? ??????"),
                                        fieldWithPath("content[].questionId").type(JsonFieldType.NUMBER).description("Question ?????????"),
                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("?????? Answer ??????"),
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
    @DisplayName("AccountAnswers_??????_??????")
    public void accountAnswersList_Success_Test() throws Exception {
        //given
        Long accountId = 1L;


        //when
        ResultActions actions = mockMvc.perform(
                get("/answers/account/{accountId}", accountId)
                        .param("page", "1")
                        .param("size","10")
                        .param("sort", "id,desc")
        );

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("AccountAnswerList",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("accountId").description("Account ?????????")
                        ),
                        requestParameters(
                                parameterWithName("page").description("????????? ??????(default = 1"),
                                parameterWithName("size").description("????????? size(default = 10)"),
                                parameterWithName("sort").description("?????? ??????(default = id, asc)")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Answer ??????"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Answer ????????????"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Answer ????????????"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Answer ?????????"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Answer ??????"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Answer Vote"),
                                        fieldWithPath("content[].selected").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Answer ????????????"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Answer ???????????? ?????????"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Answer ???????????? email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Answer ???????????? ????????? ????????? ??????"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Answer ???????????? ??????"),
                                        fieldWithPath("content[].questionId").type(JsonFieldType.NUMBER).description("Question ?????????"),
                                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???"),
                                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("?????? Answer ??????"),
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
    @DisplayName("Answer ??????_??????")
    public void answerSelect_Success_Test() throws Exception {

        //given
        Account account = accountRepository.findById(1L).get();
        String accessToken = jwtTokenizer.delegateAccessToken(account);
        String jwt = "Bearer " + accessToken;
        Long answerId = 1005L;

        //when
        ResultActions actions = mockMvc.perform(
                post("/answers/select/{answerId}", answerId)
                        .header("Authorization", jwt));

        //then
        actions
                .andExpect(status().isCreated())
                .andDo(document("selectAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        pathParameters(
                                parameterWithName("answerId").description("Answer ?????????")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("Api ?????? ?????????")
                                )
                        )
                        )
                );
    }

}
