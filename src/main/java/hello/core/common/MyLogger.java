package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
//@Scope(value = "request") //이제 이 빈은 http 요청 당 하나씩 생성되고, http 요청이 끝나는 시점에 소멸
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) { //이 빈이 생성되는 시점에는 requestURL 알 수 없으므로 setter로 입력받음
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct //빈이 생성되는 시점에 자동으로 uuid를 생성해서 저장해둠 / http 요청당 하나씩 생성
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "request scope bean create :" + this);
    }

    @PreDestroy //이 빈이 소멸되는 시점에 종료 메시지 남기고 소멸
    public void close() {
        System.out.println("[" + uuid + "]" + "request scope bean close :" + this);
    }

}
