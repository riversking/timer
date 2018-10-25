//package com.clouddeer.account.util;
//
//import com.amazonaws.ClientConfiguration;
//import com.amazonaws.Protocol;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.Bucket;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.PutObjectResult;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.UUID;
//
//
//public class AwsUploadUtil {
//
//
//    protected static AmazonS3 amazonS3 = null;
//
//    private static String accessKey = "J7CQBIVHB75ALCHKXH2T";
//
//    private static String secretKey = "ytO2gMl5bNRQD9GR9ZIww016N4DENtCE3CTekyvy";
//
//    private static String host = "http://192.168.0.155:7480/";
//
//    private static Logger logger = LoggerFactory.getLogger(AwsUploadUtil.class);
//
//
//    static {
//
//        System.out.println("开始初始化cephp配置..." + accessKey + host + secretKey);
//
//        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//
//        ClientConfiguration clientConfig = new ClientConfiguration();
//
//        clientConfig.setProtocol(Protocol.HTTP);
//
//        amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withClientConfiguration(clientConfig).withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
//                        "http://192.168.0.155:7480/",
//                        "cn-north-1"))
//                .withPathStyleAccessEnabled(true)
//                .build();
//
//        System.out.println("ceph配置初始化成功！");
//
//    }
//
//    public static String putObj(String filePath) {
//        String newFileName = UUID.randomUUID().toString();
//        Bucket b = amazonS3.createBucket("upload");
//        PutObjectResult response =  amazonS3.putObject(b.getName(),newFileName,filePath);
//        logger.info("[FileManager]Uploaded object encryption status is: {}",response.getSSEAlgorithm());
//        return "a";
//    }
//
//    public static void listObjects(String bucket_name) {
//        ObjectListing ol = amazonS3.listObjects(bucket_name);
//        //使用 getObjectSummaries 方法获取 S3ObjectSummary 对象的列表
//        List<S3ObjectSummary> objects = ol.getObjectSummaries();
//        for (S3ObjectSummary os: objects) {
//            //调用其 getKey 方法以检索对象名称
//            System.out.println("*对象名称： " + os.getKey());
//        }
//    }
//
//
//
//}
