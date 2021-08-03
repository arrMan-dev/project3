package com.revature.registry.controller;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.registry.ProjectMicroServiceApplication;
import com.revature.registry.model.Project;
import com.revature.registry.model.Tag;
import com.revature.registry.model.dto.ProjectDTO;
import com.revature.registry.model.dto.TagDTO;
import com.revature.registry.service.TagService;

@SpringBootTest(classes = ProjectMicroServiceApplication.class)
@ExtendWith(SpringExtension.class)

class TagControllerTest {

    private MockMvc mockMvc;
    
    private ModelMapper modelMapper = new ModelMapper();

    @MockBean
    private TagService tagServ;

    @Autowired
    @InjectMocks
    private TagController tagCon;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tagCon).build();
    }

    @Test
     void getAllTagsTest() throws Exception {

        Tag tag1 = new Tag();
        tag1.setId(50);
        tag1.setName("Tester tag");
        tag1.setDescription("My purpose is to exist");

        Tag tag2 = new Tag();
        tag2.setId(51);
        tag2.setName("Tester tag 2");
        tag2.setDescription("My purpose is to foil the first tag, Muhaha");

        List<Tag> tags = new ArrayList<Tag>();

        tags.add(tag1);
        tags.add(tag2);

        when(tagServ.getAllTags()).thenReturn(tags);

        mockMvc.perform(get("/api/tag")).andExpect(status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Tester tag"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Tester tag 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(51));

    }

    @Test
     void createTagTest() throws Exception {
        Tag tag1 = new Tag();
        tag1.setId(50);
        tag1.setName("Tester tag");
        tag1.setDescription("My purpose is to exist");

        when(tagServ.createTag(tag1)).thenReturn("\"Success\"");

        mockMvc.perform(post("/api/tag").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(tag1))).andExpect(status().isOk());
    }

    @Test
     void getTagByIdTest() throws Exception {
        Tag tag1 = new Tag();
        tag1.setId(50);
        tag1.setName("Tester tag");
        tag1.setDescription("My purpose is to exist");

        when(tagServ.getTagById(50)).thenReturn(tag1);

        mockMvc.perform(get("/api/tag/id/50")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tester tag"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("My purpose is to exist"));
    }
    
    @Test
    void convertToEntityTest() {
        TagDTO tagDto = new TagDTO();
        tagDto.setName("test");
        
        Tag tag = modelMapper.map(tagDto, Tag.class);
        assertEquals(tagDto.getId(),tag.getId());
        assertEquals(tagDto.getName(),tag.getName());

    }

}
