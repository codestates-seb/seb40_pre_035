package stackoverflow.domain.File.config;

import com.amazonaws.services.s3.AmazonS3Client;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stackoverflow.domain.File.repository.FileRepository;
import stackoverflow.domain.File.repository.FolderFileRepository;
import stackoverflow.domain.File.repository.S3FileRepository;

@Configuration
@RequiredArgsConstructor
public class FileConfig {

    private final AmazonS3Client amazonS3Client;

    @Bean
    @ConditionalOnProperty(value = "mod", havingValue = "local")
    public FileRepository folderFileRepository() {
        return new FolderFileRepository();
    }

    @Bean
    @ConditionalOnProperty(value = "mod", havingValue = "server")
    public FileRepository S3FileRepository() {
        return new S3FileRepository(amazonS3Client);
    }
}
