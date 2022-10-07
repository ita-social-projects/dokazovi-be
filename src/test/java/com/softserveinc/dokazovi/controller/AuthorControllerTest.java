package com.softserveinc.dokazovi.controller;

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
    private AuthorService authorService;

    @Mock
    private Validator validator;

    private final String userDTOWithId = "{\n"
            + "  \"authorId\": 1,\n"
            + "  \"avatar\": \"link\",\n"
            + "  \"bio\": \"some bio\",\n"
            + "  \"email\": \"mail@mail.com\",\n"
            + "  \"firstName\": \"John\",\n"
            + "  \"lastName\": \"Doe\",\n"
            + "  \"mainInstitutionId\": \"1\",\n"
            + "  \"password\": \"password\",\n"
            + "  \"qualification\": \"some direction\",\n"
            + "  \"socialNetwork\": [\"some links\"]\n"
            + "}";

    private final String userDTOWithoutId = "{\n"
            + "  \"authorId\": null,\n"
            + "  \"avatar\": \"link\",\n"
            + "  \"bio\": \"some bio\",\n"
            + "  \"email\": \"mail@mail.com\",\n"
            + "  \"firstName\": \"John\",\n"
            + "  \"lastName\": \"Doe\",\n"
            + "  \"mainInstitutionId\": \"1\",\n"
            + "  \"password\": \"password\",\n"
            + "  \"qualification\": \"some direction\",\n"
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
                    .content(userDTOWithoutId))
                .andExpect(status().isCreated());
    }

    @Test
    void updateAuthor() throws Exception {
        mockMvc.perform(put("/author")
                    .contentType("application/json")
                    .content(userDTOWithId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAuthor() throws Exception {
        mockMvc.perform(delete("/author/{authorId}", "1"))
                .andExpect(status().isOk());
    }
}