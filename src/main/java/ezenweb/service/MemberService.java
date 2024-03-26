package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class MemberService {
    @Autowired
    MemberEntityRepository memberEntityRepository;
    public boolean doSignupPost( MemberDto memberDto){
        memberEntityRepository.save(memberDto.toEntity());
        return true;
    }
}
