package com.rocketseat.gestao_vagas.modules.candidate.controllers;

import com.rocketseat.gestao_vagas.providers.S3StotageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class DownloadCurriculum {

    @Autowired
    private S3StotageProvider s3StotageProvider;

    @GetMapping("/curriculum/download/{fileKey}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> execute(@PathVariable String fileKey) {
        try{
            var fileBytes = s3StotageProvider.downloadFile(fileKey);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileKey)
                    .body(fileBytes);

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

}
