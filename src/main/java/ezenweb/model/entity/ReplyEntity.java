package ezenweb.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;
    private String rcontent;
    //fk필드
    @JoinColumn
    @ManyToOne
    MemberEntity memberEntity;

    @JoinColumn
    @ManyToOne
    BoardEntity boardEntity;
}
