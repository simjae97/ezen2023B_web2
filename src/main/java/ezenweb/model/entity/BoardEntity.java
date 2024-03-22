package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity//해당 클래스와 연동 DB내 테이블과 매핑
@Table(name = "board") //해당 테이블명 정의
@Builder
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int bno;

    @Column(name="title", nullable = false , unique = false , columnDefinition = "text")
    private String btitle;
    @Column(columnDefinition = "INT UNSIGNED")
    private int aaa;

    private byte 필드1;
    private short 필드2;
    private long 필드3;
    private char 필드4;
    private float 필드6;
    private double 필드7;
    private boolean 필드8;
    private LocalDateTime 필드9;

}
