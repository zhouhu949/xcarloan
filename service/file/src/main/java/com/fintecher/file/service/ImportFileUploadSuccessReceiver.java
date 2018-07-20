package com.fintecher.file.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: jwdstef
 * @Description: 接受文件上传成功队列消息
 * @Date 2017/12/4
 */
@Component
//@RabbitListener(queues = "mr.cui.file.import.upload.success")
public class ImportFileUploadSuccessReceiver {

    private final Logger logger = LoggerFactory.getLogger(ImportFileUploadSuccessReceiver.class);

    @Autowired
    private UploadFileCridFsService uploadFileCridFsService;

//    @RabbitHandler
//    public void receive(ImportFileUploadSuccessMessage message) {
//        try {
//            logger.debug("收到解压文件消息 {}", message);
//            HttpHeaders headers = new HttpHeaders();
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<byte[]> response = restTemplate.exchange(message.getUploadFile().getUrl(),
//                    HttpMethod.GET, new HttpEntity<byte[]>(headers),
//                    byte[].class);
//            uploadFileCridFsService.uploadCaseFileReduce(new ByteArrayInputStream(response.getBody()), message.getUserId(), message.getUserName(), message.getBatchNum(), message.getCompanyCode());
//
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }
}
