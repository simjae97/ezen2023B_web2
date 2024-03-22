package ezenweb.model.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Entity//해당 클래스와 연동 DB내 테이블과 매핑
@Table(name = "employee")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    int eno;

    @Column(length = 10)
    String eeducation;  // 학력 ( 고졸 , 초대졸 , 대졸 )

    @Column(length = 30, nullable = false, unique = true)
    String id;          // 아이디

    @Column(nullable = false)
    String pw;          // 비밀번호

    @Column(length = 20, nullable = false)
    String ename;       // 이름

    @Column(length = 30, nullable = false, unique = true)
    String email;       // 이메일

    @Column(length = 15, nullable = false, unique = true)
    String phone;       // 전화번호

    @Column(length = 15, nullable = false)
    String address;     // 주소

    @Column(columnDefinition = "TINYINT", length = 1)
    boolean sex;        // 성별 0 남성 1 여성


    String img;         // 업로드한 이미지 이름

//    @Column(columnDefinition = "datetime default now()") gpt가 쓰지말라함
    @Column(columnDefinition = "datetime")
    LocalDateTime edate;       // 입사일 ( 등록일 )

    //====== 추가한 필드

    String salt; //암호화용

    @PrePersist
    public void prePersist() {
        if (this.edate == null) {
            this.edate = LocalDateTime.now(); // 기본값 설정
        }
    }
}
