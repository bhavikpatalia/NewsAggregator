package com.example.NewsAggregator.Responses;


import com.example.NewsAggregator.Service.NewsServiceImpl;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {
    private String title;

    private String link;

    private String description;

    private Long pubTime;

    private String imageUrl;
}
