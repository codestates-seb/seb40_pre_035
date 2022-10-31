package stackoverflow.domain.File.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.File.dto.FileDto;
import stackoverflow.domain.File.service.FileService;
import stackoverflow.global.common.dto.SingleResDto;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(value = "mod", havingValue = "local")
public class LocalFileController {

    private final FileService fileService;

    @Value("${file.img}")
    private String path;

    @PostMapping("/file")
    public ResponseEntity<SingleResDto<String>> uploadFile(@ModelAttribute FileDto fileDto) throws IOException {

        String filePath = fileService.storeFile(fileDto.getFile(), path);
        int start = filePath.lastIndexOf("/");
        String fileName = filePath.substring(start);

        return new ResponseEntity<>(new SingleResDto<>("/file" + fileName), HttpStatus.OK);
    }

    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> getImg(@PathVariable String filename) throws MalformedURLException {
        UrlResource urlResource = new UrlResource("file:" + path + filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .body(urlResource);
    }
}
