package ezenweb.controller;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 해당 컨트롤러가 데이터를 주고 받는 역할 @Controller + @ResponseBody(content-type:application/json)를 합친것
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;


    @PostMapping("/post.do")
    public boolean postBoard(){

        return boardService.postBoard();
    }
    @GetMapping("/get.do")
    public List<Object> getBoard(){
        return boardService.getBoard();
    }
    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }
    @DeleteMapping("/delete.do")
    public boolean deleteBoard(){
        return boardService.deleteBoard();
    }
}
