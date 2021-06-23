package com.chad.api.controller;

import com.chad.api.helper.Result;
import com.chad.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tag")
public class TagsController {

  @Autowired
  private TagService tagService;

  @GetMapping("hot")
  public Result hot() {
//    int i = 10 / 0;
    int limit = 6;
    Result hots = tagService.hots(limit);
    return hots;
  }
}
