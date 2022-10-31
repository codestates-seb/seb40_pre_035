package stackoverflow.domain.File.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileRepository {

    String saveFile(MultipartFile multipartFile, String path) throws IOException;
}
