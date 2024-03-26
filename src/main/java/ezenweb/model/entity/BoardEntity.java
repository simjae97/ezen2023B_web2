package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "board")
@Getter@Setter@ToString@Builder@AllArgsConstructor@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    @Column( columnDefinition = "longtext")
    private String bcontent;
    @Column
    @ColumnDefault("0")
    private int bview;

    // fk 필드
    @JoinColumn
    @ManyToOne
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();
}
