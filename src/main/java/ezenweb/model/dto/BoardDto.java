package ezenweb.model.dto;


import ezenweb.model.entity.BoardEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    //1.출력용 게시물 이미지 필드(이유: 사진명 리스트 가져오려고 Spring ------> js)
    private List<String> bimglist = new ArrayList<>();

    //2.등록용 게시물 이미지 필드(이유?? Js ------> Spring)
    private List<MultipartFile> uploadList = new ArrayList<>();

    //글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder().bcontent(this.bcontent).build();
    }


}
