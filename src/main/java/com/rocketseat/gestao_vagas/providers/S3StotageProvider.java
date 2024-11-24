package com.rocketseat.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class S3StotageProvider {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private String region;

    private static final List<String> PERMITTED_FILES = List.of("application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

    public S3StotageProvider(

            @Value("${aws.access-key}") String accessKey,
            @Value("${aws.secret-key}") String secretKey,
            @Value("${aws.region}") String region

    ) {

        this.s3Client = S3Client.builder()
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .region(Region.of(region))
                .build();

        this.region = region;
    }

    public String uploadFile(MultipartFile file) throws IOException {

        this.validateFile(file);

        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        Path tempFilePath = Files.createTempFile(fileName, null);
        file.transferTo(tempFilePath.toFile());

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(fileName) // Nome do arquivo no S3
                .build();


        s3Client.putObject(putObjectRequest, RequestBody.fromFile(tempFilePath.toFile()));

        // Remover o arquivo temporário do sistema local após upload
        Files.delete(tempFilePath);


        return "https://" + this.bucketName + ".s3." + this.region  + ".amazonaws.com/" + fileName;
    }

    public byte[] downloadFile(String fileKey) throws URISyntaxException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(this.bucketName)
                .key(fileKey)
                .build();

       var fileBytes = s3Client.getObjectAsBytes(getObjectRequest);

       return fileBytes.asByteArray();

    }

    private void validateFile(MultipartFile file) {
        if(!PERMITTED_FILES.contains(file.getContentType())){
            throw new IllegalArgumentException("Unsupported file type");
        }
    }

}
