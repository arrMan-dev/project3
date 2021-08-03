package com.revature.registry.controller;

import java.util.List;

import com.revature.registry.model.Tag;

import com.revature.registry.model.dto.TagDTO;
import com.revature.registry.service.TagService;

import org.apache.http.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tag", produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(origins = "*")
public class TagController {

    private TagService tServ;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TagController(TagService tServ) {
        this.tServ = tServ;
    }

    @GetMapping("")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tList = tServ.getAllTags();
        return new ResponseEntity<>(tList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createTag(@RequestBody TagDTO tagDto) {
        Tag tag = convertToEntity(tagDto);
        
        String newTag = tServ.createTag(tag);

        return new ResponseEntity<>(newTag, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") int id) {
        Tag tagId = tServ.getTagById(id);
        return new ResponseEntity<>(tagId, HttpStatus.OK);
    }
    
    private Tag convertToEntity(TagDTO tagDto) throws ParseException {
        return modelMapper.map(tagDto, Tag.class);

    }
}
