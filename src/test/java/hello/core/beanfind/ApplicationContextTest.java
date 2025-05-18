package hello.core.beanfind;

import ch.qos.logback.core.joran.util.beans.BeanDescriptionFactory;
import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력해보기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();//스프링에 등록된 모든 빈 이름을 조회

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); //타입을 모르기에, 지정안했기때문에 Object타입으로 설정
            System.out.println("name = " + beanDefinitionName + "object =" + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력해보기") //우리가 만든것만 출력해보기
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //스프링에 등록된 모든 빈 이름을 조회
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){ //ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
                                                                            // <-> ROLE_INFRASTRUCTURE : 스프링이 내부적으로 사용하는 빈
                Object bean = ac.getBean(beanDefinitionName); //getbean : bean이름으로 빈 객체(인스턴스)를 조회
                System.out.println("name = " + beanDefinitionName + "object =" + bean);
            }
        }
    }
}
