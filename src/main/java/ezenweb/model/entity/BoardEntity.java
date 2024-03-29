package ezenweb.model.entity;

import ezenweb.model.dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "board")
@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
@SuperBuilder
public class BoardEntity extends BaseTime{
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

    public BoardDto toDto(){
        return BoardDto.builder()
                .bcontent(this.bcontent)
                .bno(this.bno)
                .bview(this.bview)
                .mno_fk(this.memberEntity.getMno())
                .memail(this.memberEntity.getMemail())
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .build();
    }
}
