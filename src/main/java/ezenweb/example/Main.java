package ezenweb.example;

public class Main {
    public static void main(String[] args) {

        //1.회원가입
        Employee member1 = Employee.builder()
                            .eid("qwe")
                            .eno(1)
                            .name("유재석")
                            .build();
        // 2. "유재석"이 게시물 작성
        Board board1 = Board.builder()
                            .bno(1)
                            .content("내용")
                            .title("제목")
                            .작성자(member1)
                            .build();
        //3.게시물을 작성한 회원정보 호출[게시물]단방향
        System.out.println(board1.get작성자());
        //==========================양방향======================================//
        member1.get내가쓴글().add(board1);
        System.out.println("member1.get내가쓴글() = " + member1.get내가쓴글());

        //4.유재석
        Board b2 = Board.builder().bno(2).title("제목2").content("내용2").build();

        member1.get내가쓴글().add(b2);
        System.out.println("member1.get내가쓴글() = " + member1.get내가쓴글());
    }
}
