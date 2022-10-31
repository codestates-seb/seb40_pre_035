package stackoverflow.domain.File.repository;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
public class S3FileRepository implements FileRepository {

    private String S3Bucket = "travel-plan-repo-file";

    private final AmazonS3Client amazonS3Client;

    @Override
    public String saveFile(MultipartFile multipartFile, String path) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = getStoreFileName(originalFilename, path);

        long size = multipartFile.getSize();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(size);

        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, storeFileName, multipartFile.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String imgPath = amazonS3Client.getUrl(S3Bucket, storeFileName).toString();

        return imgPath;
    }

    private String getStoreFileName(String originalFilename, String path) {
        UUID uuid = UUID.randomUUID();
        String ext = getExt(originalFilename);
        return path + uuid + "." + ext;
    }

    private static String getExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
