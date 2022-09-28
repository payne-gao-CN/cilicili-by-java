package live.cilicili.controller;

import live.cilicili.service.IOssService;
import live.cilicili.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: payne
 * @createDate: 2022/9/28 9:40
 * @description:
 */
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

  @Autowired
  private IOssService iOssService;


  @RequestMapping("/removeOssFile")
  public JsonData removeOssFile(@RequestParam("fileUrl") String fileUrl){
    iOssService.removeFile(fileUrl);
    return JsonData.buildSuccess();
  }

}
