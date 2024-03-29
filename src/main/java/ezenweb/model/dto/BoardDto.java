package ezenweb.model.dto;


import ezenweb.model.entity.BoardEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@SuperBuilder
public class BoardDto extends BaseTimeDto {

    private int bno;
    private String bcontent;
    private int bview;
    private int mno_fk; //작성자 회원번호
    private String memail; //회원 아이디

    //글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder().bcontent(this.bcontent).build();
    }


}
