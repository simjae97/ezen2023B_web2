package ezenweb.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@ToString@AllArgsConstructor@NoArgsConstructor@Getter@Setter@SuperBuilder
public class BaseTimeDto {

    private LocalDateTime cdate;

    private LocalDateTime udate;
}
