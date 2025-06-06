package hello.core.scope;

import hello.core.lifecycle.BeanLifeCycleTest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        // AnnotationConfigApplicationContext의 파라미터로 넣는 PrototypeBean 자체가 component scan 되는것
        System.out.println("prototypeBeanFind1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBeanFind2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 : " + prototypeBean1);
        System.out.println("prototypeBean2 : " + prototypeBean2);

        ac.close();
    }


    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean destroy");
        }
    }
}
