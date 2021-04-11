# 스프링 컨벤션

1. 글로벌 설정
    - Application 위에 annotation 달도록

# 개발 컨벤션

1. 서비스 dto 네이밍 컨벤션
    - `{verb}{Domain}{Request[, Response]}`
        - ex: SingUpUserRequest
2. 서비스 dto 구조
    - 기본 어노테이션
        - `@Getter`
        - `@ToString`
        - `@RequiredArgsConstructor`
    - 필드는 final로 선언

# JPA 엔티티 컨벤션

1. enum 타입
    - 무조건 묻지도 따지지도 않고 STRING 타입으로
2. N:N 관계
    - 자동 생성 금지

# 테이블 네이밍 컨벤션

1. 테이블 이름
    - 엔티티와 동일한 이름(단, DB 예약어는 피하는 방향으로)
2. PK 이름
    - id로 통일
        - primary key
        - auto increment
        - bigint
3. 제약조건 이름
    - `{constraint}_{table_name}_{column_name}`
        - ex: UQ_USER_EMAIL
        - ex: PK_USER_ID
