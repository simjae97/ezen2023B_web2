package ezenweb.model.repository;

import ezenweb.model.entity.MemberEntity;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity,Integer> {

    //1.특정 필드의 조건으로 레코드를 찾는 추상메소드 정의
    MemberEntity findByMemail(String memail);
    //2.특정 필드의 조건으로 존재여부검색
    boolean existsByMemail(String memail);

    //3.직접 naitive sql 지원
    //sql 매개변수 대입
    // :매개변수명
    @Query(value = "select * from member where memail = :memail", nativeQuery = true)
    MemberEntity findByMemailSql(String memail); //얘는 이름 맘대로 지어도 됨

    //======================================로그인==========================///
    //1.
    MemberEntity findByMemailAndMpassword(String memail,String password);
    //2.
    boolean existsByMemailAndMpassword(String memail , String password);
    //3.
    @Query(value = "select * from member where memail = :memail and mpassword = :mpassword",nativeQuery = true)
    MemberEntity findByLoginSql(String memail , String mpassword);

    //=========sorkTmsrmf=======================//;
    //1.양방향일떄는 회원 엔티티를 통해 게시물 호출 가능

    //2.단방향일때는 회원 엔티티를 이용한 게시물 호출할떄에는 조인쿼리

    @Query(value = "select * from member as m inner join board as b on m.mno = b.member_entity_mno where m.mno = :mno",nativeQuery = true)
    List<Map<Object,Object>> findByMyBoardsql(int mno);
}

/*
    추상메소드는 구현부 x
    1.특정 필드의 조건으로 레코드 엔티티 검색
    반환타입 findBy필드명
    2. 특정 필드의 조건으로 존재 여부 검색
*/
