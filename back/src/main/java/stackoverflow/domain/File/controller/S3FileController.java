package stackoverflow.domain.File.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import stackoverflow.domain.File.dto.FileDto;
import stackoverflow.domain.File.service.FileService;
import stackoverflow.global.common.dto.SingleResDto;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(value = "mod", havingValue = "server")
public class S3FileController {

    private final FileService fileService;

    @Value("${file.img}")
    private String path;

    @PostMapping("/file")
    public ResponseEntity<SingleResDto<String>> uploadFile(@ModelAttribute FileDto fileDto) throws IOException {

        String filePath = fileService.storeFile(fileDto.getFile(), path);

        return new ResponseEntity<>(new SingleResDto<>(filePath), HttpStatus.OK);
    }

}
