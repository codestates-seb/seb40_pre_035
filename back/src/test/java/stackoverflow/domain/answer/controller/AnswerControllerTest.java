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
    @DisplayName("Answer 생성_성공")
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
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                                fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("Question 식별자")
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
                                        parameterWithName("answerId").description("Answer 식별자")
                                ),
                                requestHeaders(
                                        List.of(
                                                headerWithName("Authorization").description("JWT")
                                        )
                                ),
                                requestFields(
                                        List.of(
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
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
                                        fieldWithPath("account.nickname").type(JsonFieldType.STRING).description("Answer 생성계정 별칭"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("Question 식별자"),
                                        fieldWithPath("selected").type(JsonFieldType.BOOLEAN).description("채택 여부")

                                )
                        )
                ));
    }


    //------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Answers 조회_성공")
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
                                parameterWithName("page").description("페이지 번호(default = 1"),
                                parameterWithName("size").description("페이징 size(default = 10)"),
                                parameterWithName("sort").description("정렬 조건(default = id, desc")
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
                                        fieldWithPath("content[].selected").type(JsonFieldType.BOOLEAN).description("채택 여부"),

                                        fieldWithPath("content[].questionId").type(JsonFieldType.NUMBER).description("Question 식별자"),

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

    @Test
    @DisplayName("QuestionAnswers_조회_성공")
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
                                parameterWithName("questionId").description("Question 식별자")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호(default = 1"),
                                parameterWithName("size").description("페이징 size(default = 10)"),
                                parameterWithName("sort").description("정렬 조건(default = id, asc)")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Answer 목록"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Answer 생성일자"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Answer 수정일자"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Answer 식별자"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Answer 내용"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Answer Vote"),
                                        fieldWithPath("content[].selected").type(JsonFieldType.BOOLEAN).description("채택 여부"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Answer 생성계정"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Answer 생성계정 식별자"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Answer 생성계정 email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Answer 생성계정 프로필 이미지 경로"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Answer 생성계정 별칭"),
                                        fieldWithPath("content[].questionId").type(JsonFieldType.NUMBER).description("Question 식별자"),
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

    @Test
    @DisplayName("AccountAnswers_조회_성공")
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
                                parameterWithName("accountId").description("Account 식별자")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호(default = 1"),
                                parameterWithName("size").description("페이징 size(default = 10)"),
                                parameterWithName("sort").description("정렬 조건(default = id, asc)")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("content[]").type(JsonFieldType.ARRAY).description("Answer 목록"),
                                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("Answer 생성일자"),
                                        fieldWithPath("content[].modifiedAt").type(JsonFieldType.STRING).description("Answer 수정일자"),
                                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("Answer 식별자"),
                                        fieldWithPath("content[].content").type(JsonFieldType.STRING).description("Answer 내용"),
                                        fieldWithPath("content[].totalVote").type(JsonFieldType.NUMBER).description("Answer Vote"),
                                        fieldWithPath("content[].selected").type(JsonFieldType.BOOLEAN).description("채택 여부"),
                                        fieldWithPath("content[].account").type(JsonFieldType.OBJECT).description("Answer 생성계정"),
                                        fieldWithPath("content[].account.id").type(JsonFieldType.NUMBER).description("Answer 생성계정 식별자"),
                                        fieldWithPath("content[].account.email").type(JsonFieldType.STRING).description("Answer 생성계정 email"),
                                        fieldWithPath("content[].account.profile").type(JsonFieldType.STRING).description("Answer 생성계정 프로필 이미지 경로"),
                                        fieldWithPath("content[].account.nickname").type(JsonFieldType.STRING).description("Answer 생성계정 별칭"),
                                        fieldWithPath("content[].questionId").type(JsonFieldType.NUMBER).description("Question 식별자"),
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

    @Test
    @DisplayName("Answer 채택_성공")
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
                                parameterWithName("answerId").description("Answer 식별자")
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
