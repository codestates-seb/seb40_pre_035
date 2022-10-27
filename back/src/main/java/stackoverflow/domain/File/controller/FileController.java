package stackoverflow.domain.File.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import stackoverflow.domain.File.dto.FileDto;
import stackoverflow.global.common.dto.SingleResDto;

@RestController
@RequiredArgsConstructor
public class FileController {

    @PostMapping("/file")
    public ResponseEntity<SingleResDto<String>> uploadFile(@ModelAttribute FileDto fileDto) {

        SingleResDto<String> response =
                new SingleResDto<>("https://travel-plan-repo-file.s3.ap-northeast-2.amazonaws.com/ProfileImg/00a76424-94a7-4683-aa0a-cbb0e683586b.jpeg");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
