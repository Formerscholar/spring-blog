package com.chad.api.vo;

import lombok.Data;

@Data
public class PageVo {
  private Object data;
  private Long current_page;
  private Long last_page;
  private Long per_page;
  private Long total;
}
