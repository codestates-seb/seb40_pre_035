package stackoverflow.domain.account.controller;

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
import stackoverflow.domain.account.dto.PatchAccountReqDto;
import stackoverflow.domain.account.dto.PostAccountReqDto;
import stackoverflow.global.security.auth.dto.LoginDto;

import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
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
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

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
        String nickname = "moc2";


        PostAccountReqDto postAccountReqDto = new PostAccountReqDto();
        postAccountReqDto.setEmail(email);
        postAccountReqDto.setPassword(password);
        postAccountReqDto.setNickname(nickname);

        String body = gson.toJson(postAccountReqDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/accounts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );

        //then
        actions.andExpect(status().isCreated())
                .andDo(document(
                        "createAccount",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀 번호"),
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("이름")
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
                                        fieldWithPath("profile").type(JsonFieldType.STRING).description("프로필 이미지 경로")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Account 수정_성공")
    void accountModify() throws Exception {
        //given
        long accountId = 1L;
        String nickname = "testModifiedNickname";
        String password = "testModified1234";
        String profile = "/path/test/modified";
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";

        PatchAccountReqDto patchAccountReqDto = new PatchAccountReqDto();
        patchAccountReqDto.setNickname(nickname);
        patchAccountReqDto.setPassword(password);
        patchAccountReqDto.setProfile(profile);

        String body = gson.toJson(patchAccountReqDto);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/accounts/{accountId}", accountId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .content(body)
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
                        requestFields(
                                List.of(
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀 번호"),
                                        fieldWithPath("profile").type(JsonFieldType.STRING).description("프로필 이미지 경로")
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
        long accountId = 1L;
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";

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
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";

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