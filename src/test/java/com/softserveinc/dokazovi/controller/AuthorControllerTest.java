package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.mapper.AuthorMapper;
import com.softserveinc.dokazovi.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthorControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorMapper authorMapper;
    @Mock
    private AuthorService authorService;

    @Mock
    private Validator validator;

    private final String userDTO = "{\n"
            + "  \"firstName\": \"testName\",\n"
            + "  \"lastName\": \"testLastNAme\",\n"
            + "  \"cityId\": \"190\",\n"
            + "  \"avatar\": \"avatar\",\n"
            + "  \"mainWorkingPlace\": \"Hospital\",\n"
            + "  \"bio\": \"bio\",\n"
            + "  \"socialNetwork\": [\"some links\"]\n"
            + "}";

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(authorController)
                .setValidator(validator)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void createAuthor() throws Exception {
        mockMvc.perform(post("/author")
                    .contentType("application/json")
                    .content(userDTO))
                .andExpect(status().isCreated());
    }

    @Test
    void updateAuthor() throws Exception {
        mockMvc.perform(put("/author")
                    .contentType("application/json")
                    .content(userDTO))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAuthor() throws Exception {
        mockMvc.perform(delete("/author/{authorId}", "1"))
                .andExpect(status().isOk());
    }
}