package com.revature.registry.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.registry.ProjectMicroServiceApplication;
import com.revature.registry.model.Tag;
import com.revature.registry.repository.TagRepository;

@SpringBootTest(classes = ProjectMicroServiceApplication.class)
class TagServiceTest {
    @MockBean
    TagRepository tagRepository;

    @Autowired
    TagService tagService;

    // Static Variables
    static Tag tag1;
    static Tag tag2;

    @BeforeEach
    void setUp() {
        List<Tag> tagList = new LinkedList<>();
        tag1 = new Tag();
        tag1.setId(10);
        tag2 = new Tag();
        tag2.setId(1);
        tagList.add(tag1);

        // Mock Repo
        when(tagRepository.findAll()).thenReturn(tagList);
        when(tagRepository.findById(tag1.getId())).thenReturn(Optional.ofNullable(tag1));
        when(tagRepository.save(tag2)).thenReturn(tag2);

    }

    @Test
    void testGetTagListNotEmpty() {
        assertThat(tagService.getAllTags()).isNotEmpty();
    }

    @Test
    void testGetTagByIdReturnTag() {
        assertThat(tagService.getTagById(tag1.getId())).isEqualTo(tag1);
    }

    @Test
    void testGetTagByIdReturnNull() {
        assertThat(tagService.getTagById(1)).isNull();
    }

    @Test
    void testCreateTagReturnString() {
        assertThat(tagService.createTag(tag2)).isEqualTo("\"Success\"");
    }

}
