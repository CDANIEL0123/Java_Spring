package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    } //이 싱글톤 인스턴스의 참조를 꺼낼수있는건 얘밖에 없음 => 싱글톤 구현된것임

    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
