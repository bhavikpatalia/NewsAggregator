package com.example.NewsAggregator.Responses;


import lombok.*;

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
