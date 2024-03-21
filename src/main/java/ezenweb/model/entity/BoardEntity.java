package ezenweb.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class BoardEntity {
    @Id
    private int bno;
    private String btitle;

}
