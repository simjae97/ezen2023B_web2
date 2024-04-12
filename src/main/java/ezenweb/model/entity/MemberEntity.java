package ezenweb.model.entity;

import ezenweb.example.Board;
import ezenweb.model.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
@SuperBuilder@DynamicInsert
public class MemberEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    @Column(length = 50 , unique = true)
    private String memail;

    private String mpassword;
    @Column(length = 20)
    private String mname;
    @Column( name = "mrole"  ) // varchar(255) , not null
    @ColumnDefault( "'USER'") // 문자 '' , 숫자 // ??????
    private String mrole;

    //양방향 : 게시물 fk
    @OneToMany(mappedBy = "memberEntity" ,cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<BoardEntity> boardEntityList = new ArrayList<>();
    //양방향 : 댓글 fk
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL) //자바 필드명으로 넣어야함 ************
    @ToString.Exclude //해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default //빌더시 기본값 넣기위해 사용
    private List<ReplyEntity> replyEntityList = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        if (this.mrole == null) {
            this.mrole = "user";
        }

    }

    // 엔티티를 dto로 변환하는 메소드
    public MemberDto toDto(){
        return MemberDto.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mrole(this.mrole)
                .mname(this.mname)
                .build();

    }
}