package stackoverflow.domain.File.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stackoverflow.domain.File.repository.FileRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public String storeFile(MultipartFile multipartFile, String path) throws IOException {

        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        verifiedFilename(multipartFile.getOriginalFilename());

        return fileRepository.saveFile(multipartFile, path);
    }

    private void verifiedFilename(String filename) {
        if (filename == null) {
            throw new BusinessLogicException(ExceptionCode.ILLEGAL_FILENAME);
        }
    }
}
