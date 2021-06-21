package az.code.myauto.utils;

import az.code.myauto.config.Properties;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import lombok.Data;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadUtil {
    final
    Properties properties;
    private static final String MY_URL = "https://firebasestorage.googleapis.com/v0/b/my-auto-5679d.appspot.com/o/%s?alt=media";
    private static String TEMP_URL = "";

    public FileUploadUtil(Properties properties) {
        this.properties = properties;
    }

    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            ClassPathResource serviceAccount = new ClassPathResource("firebase.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                    .setStorageBucket(properties.getBucketName())
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("my-auto-5679d.appspot.com", fileName);
        String type = Files.probeContentType(file.toPath());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(type).build();
        Path path = Paths.get("src/main/resources/firebase.json");
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(path.toAbsolutePath().toString()));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(MY_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.
            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return TEMP_URL;
        } catch (Exception e) {
            e.printStackTrace();
            return "500 Unsuccessfully Uploaded!";
        }

    }

    public void delete(String url) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(url);
        blob.delete();
    }


}
