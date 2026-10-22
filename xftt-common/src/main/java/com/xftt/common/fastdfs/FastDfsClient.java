package com.xftt.common.fastdfs;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * 心飞头条 - 对象存储客户端（MinIO 实现，兼容原 FastDFS 调用方式）
 */
@Component
public class FastDfsClient {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private FastDfsConfig fastDfsConfig;

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(FileUtils.readFileToByteArray(new File(fileName)), extName);
    }

    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        String objectName = UUID.randomUUID().toString().replace("-", "");
        if (StringUtils.isNotBlank(extName)) {
            objectName = objectName + "." + extName;
        }
        try (InputStream in = new ByteArrayInputStream(fileContent)) {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(fastDfsConfig.getBucket())
                    .object(objectName)
                    .stream(in, fileContent.length, -1)
                    .build());
        }
        return objectName;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null);
    }

    public byte[] downFile(String fileId) throws Exception {
        return downGroupFile("", fileId);
    }

    public byte[] downGroupFile(String group, String fileId) throws Exception {
        String objectName = StringUtils.isBlank(group) ? fileId : group + "/" + fileId;
        try (InputStream in = minioClient.getObject(GetObjectArgs.builder()
                .bucket(fastDfsConfig.getBucket())
                .object(objectName)
                .build());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        }
    }

    public int delFile(String fileId) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(fastDfsConfig.getBucket())
                .object(fileId)
                .build());
        return 1;
    }
}
