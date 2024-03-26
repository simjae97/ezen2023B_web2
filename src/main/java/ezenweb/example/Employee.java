package ezenweb.example;


import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor@NoArgsConstructor
@Builder@Getter@ToString@Setter
public class Employee {
    private int eno; //회원번호
    private String eid; //아이디
    private String name; //이름
    @ToString.Exclude //toString할떄 배제하고 실행하겠다.
    @Builder.Default
    private List<Board> 내가쓴글 = new ArrayList<>();

    @ToString.Exclude //toString할떄 배제하고 실행하겠다.
    @Builder.Default
    private List<댓글> 내가쓴댓글 =new ArrayList<>();
}
