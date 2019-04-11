package com.example.feignclientcompressdemo;

import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class FeignClientCompressDemoApplication {

  // by default no Client bean defined, thus FeignAcceptGzipEncodingInterceptor won't be auto configured.
//  @Bean
//  Client feignClient() {
//    return new Client.Default(HttpsURLConnection.getDefaultSSLSocketFactory(), HttpsURLConnection.getDefaultHostnameVerifier());
//  }

  /**
   The FeignAcceptGzipEncodingInterceptor add "Accept-Encoding: gzip, deflate" to
   request header. When the server response with deflate stream, the URLConnection-based
   default client can handle it well. But if the server response with gzip content,
   the Decoder won't able to handle the compressed response well and exception will be
   thrown.
  */
  @Bean
  RequestInterceptor gzipInterceptor() {
    return template -> template.header("Accept-Encoding", "gzip");
  }

  public static void main(String[] args) {
    SpringApplication.run(FeignClientCompressDemoApplication.class, args);
  }

}
