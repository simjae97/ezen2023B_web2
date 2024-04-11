package ezenweb.model.dto;


import ezenweb.model.entity.BaseTime;
import ezenweb.model.entity.MemberEntity;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
                .mpassword(new BCryptPasswordEncoder().encode(this.mpassword)) //암호화할 데이터를 넣어줌
                /*암호화란 :
                    암호 : 정보를 이해할수 없도록 = 사람이 이해할 수 없도록 난수로 변경
                        -이해할 수 없도록 자기만의 방법으로 변경
                        -종류 : 1.bcrypt , AES-256
                 */
                .memail(this.memail)
                .mname(this.mname)
                .build();
        return memberEntity;

    }
}
