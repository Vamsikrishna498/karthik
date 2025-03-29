package com.farmer.farmermanagement.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    // Bean to initialize FirebaseApp
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        // Load the service account JSON file from the classpath (resources)
        Resource resource = new ClassPathResource("firebase-service-account.json");
        
        // Check if the resource exists
        if (!resource.exists()) {
            throw new IOException("Firebase service account file not found in classpath.");
        }
        
        InputStream serviceAccount = resource.getInputStream();

        // Set up the FirebaseOptions using the service account credentials
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        // Initialize the FirebaseApp instance
        return FirebaseApp.initializeApp(options);
    }

    // Bean to initialize FirebaseAuth
    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        return FirebaseAuth.getInstance(firebaseApp());
    }
}
