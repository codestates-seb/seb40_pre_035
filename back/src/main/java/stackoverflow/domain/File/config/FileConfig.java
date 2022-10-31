package stackoverflow.domain.File.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stackoverflow.domain.File.repository.FileRepository;
import stackoverflow.domain.File.repository.FolderFileRepository;

@Configuration
@RequiredArgsConstructor
public class FileConfig {

    @Bean
    @ConditionalOnProperty(value = "mod", havingValue = "local")
    public FileRepository folderFileRepository() {
        return new FolderFileRepository();
    }
}
