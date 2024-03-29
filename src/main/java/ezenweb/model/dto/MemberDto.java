package ezenweb.model.dto;


import ezenweb.model.entity.BaseTime;
import ezenweb.model.entity.MemberEntity;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor@NoArgsConstructor@ToString@SuperBuilder@Getter@Setter
public class MemberDto extends BaseTimeDto{


    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrole;


    // - 엔티티로 변환하는 메소드
    //가입용
    public MemberEntity toEntity(){
        MemberEntity memberEntity= MemberEntity.builder()
                .mpassword(this.mpassword)
                .memail(this.memail)
                .mname(this.mname)
                .build();
        return memberEntity;

    }
}
