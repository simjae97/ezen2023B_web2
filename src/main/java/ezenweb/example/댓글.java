package ezenweb.example;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Setter
public class 댓글 {
    private int 번호;
    private String 내용;
    //작성자 회원객체
    private Employee 작성자;
    private Board 게시물;

}
