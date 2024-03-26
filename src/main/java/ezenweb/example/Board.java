package ezenweb.example;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Setter
public class Board {
    private int bno;
    private String title;
    private String content;
    //작성자 회원객체
    private Employee 작성자;
    @ToString.Exclude //toString할떄 배제하고 실행하겠다.
    @Builder.Default
    private List<댓글> 이게시물에달린댓글 =new ArrayList<>();
}
