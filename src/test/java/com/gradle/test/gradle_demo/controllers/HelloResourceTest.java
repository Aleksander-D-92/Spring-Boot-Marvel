package com.gradle.test.gradle_demo.controllers;

import com.gradle.test.gradle_demo.services.HelloServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class})
class HelloResourceTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private HelloServiceImpl helloService;
    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void helloWorld() throws Exception {
        Mockito.when(helloService.hello()).thenReturn("hello");
        mvc
                .perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"))
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
        Mockito.verify(helloService).hello();
    }

    @Test
    void json() throws Exception {
        mvc
                .perform(MockMvcRequestBuilders.get("/json").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title-name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is("value-name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2)))
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));

    }

    @Test
    void postJson() throws Exception {
        String json = "{\n" +
                "  \"title\": \"titleVal\",\n" +
                "  \"value\": \"stringVal\"\n" +
                "}";
        mvc
                .perform(MockMvcRequestBuilders.post("/json").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("titleVal")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is("stringVal")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2)))
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));

    }
}
