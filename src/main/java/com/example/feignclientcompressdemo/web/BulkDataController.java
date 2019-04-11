package com.example.feignclientcompressdemo.web;

import com.example.feignclientcompressdemo.remote.RemoteClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class BulkDataController {
  private static final int MAX_KEY = 1024;
  private static final int CONTENT_LENGTH = 1024;
  private static final char[]  CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,.!?".toCharArray();

  private RemoteClient remoteClient;

  public BulkDataController(RemoteClient remoteClient) {
    this.remoteClient = remoteClient;
  }

  @GetMapping(value = "/bulkData", produces = MediaType.APPLICATION_JSON_VALUE)
  Map<String, Object> getBulkData() {
    Map<String, Object> map = new HashMap<>();
    for (int i = 0; i < MAX_KEY; ++i) {
      map.put("key" + i, buildRandomContent());
    }
    return map;
  }

  private String buildRandomContent() {
    StringBuilder sb = new StringBuilder(CONTENT_LENGTH);
    int size = CHARS.length;
    Random random = new Random(System.currentTimeMillis());
    for (int i = 0; i < CONTENT_LENGTH; ++i) {
      sb.append(CHARS[random.nextInt(size)]);
    }
    return sb.toString();
  }

  @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
  Object test() {
    return remoteClient.getBulkData();
  }
}
