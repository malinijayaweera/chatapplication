package com.javamaster.messagingapp.controller;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


import com.javamaster.messagingapp.model.MessageModel;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message send (final Message message) throws Exception{
        return message;
    }
    public MessageModel receiveMessage(@Payload MessageModel message){
        String imageData = message.getImageData();
        byte[] decodedImageData = Base64.getDecoder().decode(imageData);
        byte[] processedImageData = performImageProcessing(decodedImageData, 800, 600);
        message.setProcessedImageData(Base64.getEncoder().encodeToString(processedImageData));

        return message;
    }

    private byte[] performImageProcessing(byte[] imageData, int width, int height) {
        try {
            // Decode the image data into a BufferedImage
            ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
            BufferedImage image = ImageIO.read(bais);
    
            // Resize the image using BufferedImage
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(image, 0, 0, width, height, null);
            g2d.dispose();
    
            // Encode the resized image back into a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            // Handle the exception appropriately
        }
    
        return null; // Or handle the failure case accordingly
    }

    @MessageMapping("/private-message")
    public MessageModel recMessage(@Payload MessageModel message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }
}