# FDS

## 개발환경
>JDK 1.8.0_151  
Gradle 4.8.1  
Spring Boot 2.0.4.RELEASE  
Intellij 

---

## 실행방법
```
 ./gradlew clean bootRun
```
또는 
```
 ./gradlew clean build 

java -jar build/libs/fds-0.0.1-SNAPSHOT.jar
```

---

## 코드설명 

No | package                   |    filename     | description
---|--------------------------|-----------------|-------------
1|com.kakaopay.fds | FdsApplication  | SpringBoot Main 함수 클래스 
2|com.kakaopay.fds.api.config | H2Configuration | H2 console 접근을 위한 설정 클래스 
3|com.kakaopay.fds.api.controller | FdsControllerV1 | Rest API 구현 클래스 
4|com.kakaopay.fds.api.entity | BaseEntity | KakaoLog 공통 Entity 클래스
5| | KakaoChargeLog | 카카오머니 충전 로그 Entity 클래스 
6| | KakaoReceiverLog | 카카오머니 받기 로그 Entity 클래스
7| | KakaoSenderLog | 카카오머니 송금 로그 Entity 클래스 
8| | KakaoServiceAccountOpenLog | 카카오머니 계좌 개설 로그 Entity 클래스 
9| com.kakaopay.fds.api.repository | KakaoChargeLogRepository | 카카오머니 충전 로그 JpaRepository
10| | KakaoReceiverLogRepository | 카카오머니 받기 로그 JpaRepository
11| |KakaoSenderLogRepository | 카카오머니 송금 로그 JpaRepository
12| |KakaoServiceAccountOpenLogRepository | 카카오머니 계좌 개설 로그 JpaRepository
13|com.kakaopay.fds.api.reponse | FdsResponse | Rest API 응답 객체 클래스
14|com.kakaopay.fds.api.service | FdsService | Rule 검출을 위한 인터페이스
15| |FdsServiceImpl | Rule 검출을 위한 비지니스 로직을 다루는 클래스
16|com.kakaopay.fds.core.config | RuleConfigLoader | rule.properties 파일의 속성 값을 읽기위한 클래스 
17|com.kakaopay.fds.core.constant | RuleConfigConstant | rule.properties 속성 값이 정의된 클래스
18| | RuleEnum | Log Type 구별을 위한 Enum 클래스
19|com.kakaopay.fds.core.dto | KakaoMoneyLog | Log Type과 카카오 로그로 구성된 클래스
20| | RuleDataSet | Log List와 Date Range로 구성된 클래스 
21|com.kakaopay.fds.executor | FdsEngine | Rule 매칭을 담당하는 클래스
22|com.kakaopay.fds.rule | Rule | Rule 인터페이스
23| | RuleA | RuleA 검출을 위한 구현 클래스
24| | RuleB | RuleB 검출을 위한 구현 클래스
25| | RuleC | RuleC 검출을 위한 구현 클래스
26|com.kakaopay.fds.util | AmountUtil | 전달받은 로그의 금액을 필터링, 합산하는 함수 구현 클래스 
27| | DateUtil | 날짜 관련 처리함수 구현 클래스 
28| | KakaoLogUtil | 계좌 개설 로그 추출, Rule에 해당하는 로그 추출, Rule 해당 로그를 counting하는 함수 구현 클래스
29| | PredicateUtil | 비교 Predicate 구현 클래스 