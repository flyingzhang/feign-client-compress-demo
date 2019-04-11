package com.example.feignclientcompressdemo.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "local", url = "http://localhost:8080/")
public interface RemoteClient {

  @GetMapping("/bulkData")
  Object getBulkData();
}
