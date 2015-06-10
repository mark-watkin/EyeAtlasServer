package nz.ac.aucklanduni.util;

import com.amazonaws.auth.AWSCredentials;

public class S3Properties implements AWSCredentials{

    private String accessKey;
    private String secretKey;
    private String bucketName;

    @Override
    public String getAWSAccessKeyId() {
        return this.accessKey;
    }

    @Override
    public String getAWSSecretKey() {
        return this.secretKey;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public void setAccessKey(String awsAccessKey) {
        this.accessKey = awsAccessKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String awsSecretKey) {
        this.secretKey = awsSecretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String awsBucketName) {
        this.bucketName = awsBucketName;
    }
}