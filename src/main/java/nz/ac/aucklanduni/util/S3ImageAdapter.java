package nz.ac.aucklanduni.util;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class S3ImageAdapter {

    private static final String PROPERTY_PATH = "META-INF/properties/s3properties.properties";
    private static S3Properties properties;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            properties = mapper.readValue(S3ImageAdapter.class.getClassLoader().getResourceAsStream(PROPERTY_PATH), S3Properties.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("s3 properties cannot be initialized.");
        }
    }

    public S3ImageAdapter() {

    }

    public static void upload(String filePath) throws IOException {
        AmazonS3 s3Client = new AmazonS3Client(properties);
        s3Client.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_2));

        FileInputStream stream = new FileInputStream(filePath);
        ObjectMetadata objectMetadata = new ObjectMetadata();

        String filePathOnAmazon = filePath.substring(ImageProcessor.ROOT_FOLDER.length());
        PutObjectRequest putObjectRequest = new PutObjectRequest(properties.getBucketName(), filePathOnAmazon, stream, objectMetadata);
        PutObjectResult result = s3Client.putObject(putObjectRequest);
        System.out.println("S3: PUT " + result);

    }

    public static void delete(String filePath) throws IOException {
        AmazonS3 s3Client = new AmazonS3Client(properties);
        s3Client.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_2));

        ObjectListing listing = s3Client.listObjects(properties.getBucketName(), filePath);
        List<S3ObjectSummary> summaries = listing.getObjectSummaries();

        List<KeyVersion> keys = new ArrayList<KeyVersion>();
        for(S3ObjectSummary s : summaries) {
            keys.add(new KeyVersion(s.getKey()));
        }

        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(properties.getBucketName());
        deleteObjectsRequest.setKeys(keys);
        s3Client.deleteObjects(deleteObjectsRequest);
        System.out.println("S3: DELETE " + filePath);
    }
}