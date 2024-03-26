package ezenweb.model.repository;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 매핑된 테이블의 엔티티/레코드들을 조작/관리하는 리모콘/인터페이스 역할
public interface ReplyEntityRepository extends JpaRepository<ReplyEntity,Integer> {


}
