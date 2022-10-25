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
import stackoverflow.domain.question.dto.QuestionReq;

import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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
        String content = "testQuestionContent";
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";

        QuestionReq questionReq = new QuestionReq();
        questionReq.setTitle(title);
        questionReq.setContent(content);

        String body = gson.toJson(questionReq);

        //when
        ResultActions actions = mockMvc.perform(
                post("/question")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwt)
                        .content(body)
        );

        //then
        actions
                .andExpect(status().isOk())
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
                        )
                ));
    }

    @Test
    @DisplayName("Question 수정_성공")
    void modifyQuestion_Success_Test() throws Exception {

        //given
        long questionId = 1L;
        String jwt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1wbGUxQHNhbXBsZS5jb20iLCJpZCI6MSwiZX";

        //when
        ResultActions actions = mockMvc.perform(
                patch("/question/{questionId}", questionId)
                        .header("Authorization", jwt)
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
                delete("/question/{questionId}", questionId)
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
                        )
                ));
    }

    @Test
    void getQuestion() {
    }

    @Test
    void getQuestions() {
    }

    @Test
    void searchQuestions() {
    }
}