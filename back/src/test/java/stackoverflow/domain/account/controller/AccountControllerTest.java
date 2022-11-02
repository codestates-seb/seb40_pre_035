package stackoverflow.domain.account.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.global.security.auth.dto.LoginDto;
import stackoverflow.global.security.auth.jwt.JwtTokenizer;

import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
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
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @Autowired
    private JwtTokenizer jwtTokenizer;
    @Autowired
    private AccountRepository accountRepository;

    @Value("${file.img}")
    private String path;

    @Test
    @DisplayName("Account Login_성공")
    void accountLogin() throws Exception {
        //given
        String email = "login@gmail.com";
        String password = "mock1234";

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(password);
        String body = gson.toJson(loginDto);


        //when
        ResultActions actions = mockMvc.perform(
                post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );


        //then
        actions.andExpect(status().isOk())
                .andDo(document(
                        "loginAccount",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀 번호")
                                )
                        ),
                        responseHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("로그인 API 성공 메세지")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Account 생성_성공")
    void accountAdd() throws Exception {
        //given
        String email = "mock2@gmail.com";
        String password = "mock1234";
        String nickname = "mocknickname";
        MockMultipartFile file = new MockMultipartFile("profile", "profile", "image/jpeg",
                "file".getBytes());


        //when
        ResultActions actions = mockMvc.perform(
                multipart("/accounts")
                        .file(file)
                        .param("email", email)
                        .param("password", password)
                        .param("nickname", nickname)
        );

        //then
        actions.andExpect(status().isCreated())
                .andDo(document(
                        "createAccount",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParts(
                                List.of(
                                        partWithName("profile").description("프로필 이미지")
                                )
                        ),
                        requestParameters(
                                List.of(
                                        parameterWithName("email").description("이메일"),
                                        parameterWithName("password").description("비밀 번호"),
                                        parameterWithName("nickname").description("이름")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("등록 API 성공 메세지")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Account 조회_성공")
    void accountDetails() throws Exception {
        //given
        long accountId = 1L;

        //when
        ResultActions actions = mockMvc.perform(
                get("/accounts/{accountId}", accountId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(status().isOk())
                .andDo(document(
                        "getAccount",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("accountId").description("Account 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("profile").type(JsonFieldType.STRING).description("프로필 이미지 경로"),
                                        fieldWithPath("totalVotes").type(JsonFieldType.NUMBER).description("총 투표 수"),
                                        fieldWithPath("totalQuestions").type(JsonFieldType.NUMBER).description("총 질문 수"),
                                        fieldWithPath("totalAnswers").type(JsonFieldType.NUMBER).description("총 답변 수")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Account 수정_성공")
    void accountModify() throws Exception {
        //given
        long accountId = 1L;
        String accessToken = jwtTokenizer.delegateAccessToken(accountRepository.findById(accountId).get());
        String jwt = "Bearer " + accessToken;
        String nickname = "modifynick";
        String password = "testModified1234";
        MockMultipartFile file = new MockMultipartFile("profile", "profile", "image/jpeg",
                "file".getBytes());


        //when
        ResultActions actions = mockMvc.perform(
                multipart("/accounts/{accountId}", accountId)
                        .file(file)
                        .header("Authorization", jwt)
                        .param("nickname", nickname)
                        .param("password", password)
        );


        //then
        actions.andExpect(status().isOk())
                .andDo(document(
                        "modifyAccount",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("accountId").description("Account 식별자")
                        ),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        requestParts(
                                List.of(
                                        partWithName("profile").description("프로필 이미지")
                                )
                        ),
                        requestParameters(
                                List.of(
                                        parameterWithName("nickname").description("이름"),
                                        parameterWithName("password").description("비밀 번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("수정 API 성공 메세지")
                                )
                        )
                ));


    }

    @Test
    @DisplayName("Account 삭제_성공")
    void accountRemove() throws Exception {
        //given
        long accountId = 3L;

        String accessToken = jwtTokenizer.delegateAccessToken(accountRepository.findById(accountId).get());
        String jwt = "Bearer " + accessToken;

        //when
        ResultActions actions = mockMvc.perform(
                delete("/accounts/{accountId}", accountId)
                        .header("Authorization", jwt)
        );

        //then
        actions.andExpect(status().isOk())
                .andDo(document(
                        "deleteAccount",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("accountId").description("Account 식별자")
                        ),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("삭제 API 성공 메세지")
                                )
                        )
                ));



    }

    @Test
    @DisplayName("로그인한 Account 조회_성공")
    void accountUserDetails() throws Exception {
        //given
        long accountId = 2L;
        String accessToken = jwtTokenizer.delegateAccessToken(accountRepository.findById(accountId).get());
        String jwt = "Bearer " + accessToken;


        //when
        ResultActions actions = mockMvc.perform(
                get("/accounts/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
        );

        //then
        actions.andExpect(status().isOk())
                .andDo(document(
                        "getUserAccount",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                List.of(
                                        headerWithName("Authorization").description("JWT")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("현재 로그인 유저의 이메일"),
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("현재 로그인 유저의 이름"),
                                        fieldWithPath("profile").type(JsonFieldType.STRING).description("현재 로그인 유저의 프로필 이미지 경로")
                                )
                        )
                ));

    }
}