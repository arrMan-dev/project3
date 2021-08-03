package com.revature.registry.service;


import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import com.revature.registry.model.Tag;
import com.revature.registry.repository.TagRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class TagService {
private static Logger log = Logger.getLogger(TagService.class);

    
    private TagRepository tRepo;

    public TagService() {
        
    }
    @Autowired
    public TagService(TagRepository tRepo) {
        this.tRepo = tRepo;
    }

    public List<Tag> getAllTags() {
        log.info("Getting all tags: "+ tRepo.findAll());
        return tRepo.findAll();
    }
    
    public Tag getTagById(int id) {
        log.debug("Getting tag by Id");
        Optional<Tag> tag = tRepo.findById(id);
        if (tag.isPresent()) {
            log.info("Fetching Tag with id of " + id);
            return tag.get();
        }
        log.error("Unable to GET. tag with id " + id + " not found");
        return null;
    }
    
    
    public String createTag(Tag tag) {
        Tag savedTag = tRepo.save(tag);
        log.debug("Tag created with the following properties: "+ savedTag.toString());
        if ( savedTag.getId() != 0 ) {
            return "\"Success\"";
        } else {
            return "Create Tag failed!"; 
        }
        
    }

    
}
