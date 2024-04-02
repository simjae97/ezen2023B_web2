package ezenweb.model.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "boardimg")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BoardImgEntity extends BaseTime{

    @Id
    private String bimg;

    @ManyToOne
    @JoinColumn(name = "bno")
    BoardEntity boardEntity;


}
