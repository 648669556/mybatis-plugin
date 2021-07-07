package com.chen.mybatisPlugin.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * blog
 * @author 
 */
@Data
public class Blog implements Serializable {
    private Integer id;

    private String title;

    private String url;

    private Date publish;

    private Integer views;

    private String blogername;

    private Integer ispick;

    private static final long serialVersionUID = 1L;
}