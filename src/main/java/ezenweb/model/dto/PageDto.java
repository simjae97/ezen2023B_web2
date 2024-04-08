package ezenweb.model.dto;


import lombok.*;

import java.util.List;


@Getter@Setter@ToString@AllArgsConstructor@Builder@NoArgsConstructor
public class PageDto {

    int page; // 현재페이지
    int count; // 총 페이지 수

    List<Object> data; //본문 내용들
}
