package ezenweb.model.dto;


import ezenweb.model.entity.MemberEntity;
import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor@NoArgsConstructor@ToString@Builder@Getter@Setter
public class MemberDto {


    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrole;


    // - 엔티티로 변환하는 메소드
    public MemberEntity toEntity(){
        return MemberEntity.builder().mno(this.mno).mpassword(this.mpassword).memail(this.memail).mrole(this.mrole).mname(this.mname).build();

    }
}
