package com.example.demo.controller;


import com.example.demo.entities.PostEntity;
import jakarta.persistence.*;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/posts/{postId}")
    List<PostEntity> getAllRevisions(@PathVariable Long postId){
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = reader.getRevisions(PostEntity.class, postId);

        return revisions
                .stream()
                .map(revisionNumber -> reader.find(PostEntity.class, postId, revisionNumber))
                .collect(Collectors.toList());
    }


}
