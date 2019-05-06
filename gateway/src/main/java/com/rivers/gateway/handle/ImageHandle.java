package com.rivers.gateway.handle;

import com.rivers.gateway.config.WindowsorMac;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * 图片读取
 * @author riverskingking
 */
@Component
@Log4j2
public class ImageHandle implements HandlerFunction<ServerResponse> {

    private static final String ROOT = WindowsorMac.pathName();


    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {

        //读取本地图片输入流
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            BufferedImage bimg = ImageIO.read(new File(Paths.get(ROOT, request.pathVariable("filename")).toString()));
            ImageIO.write(bimg, "png", os);
        } catch (IOException e) {
            log.error("e {}", e);
        }
        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
    }
}
