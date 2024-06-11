package com.atdev.payload.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MainResponse {

    private String message;

    private Boolean flag;

    private Integer responseCode;
}
