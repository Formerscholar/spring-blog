package com.chad.api.handler;

import com.chad.api.helper.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Result doException(Exception ex) {
    ex.printStackTrace();
    return Result.fail(-999, "系统异常");
  }
}
