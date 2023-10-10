# ERP 연차 관리 API

## 기술 스택
- Spring Boot 3.1.2
- Spring Security 6.1.2
- Java 17.0.7
- MySQL Community 8.0

## 테이블 설계
- 외래키 존재 여부는 CASCADE 사용 여부에 따라 설정했습니다.
<img width="600" alt="tables" src="https://github.com/devops3199/erp-hr/assets/8262598/3818933d-13ee-441f-a6da-36a9a68bf3d1">

## 서비스 설계
- JWT 기반 응답 서비스입니다.
  - HTTP HEADER에 `Authorization: Bearer eyabc12345`가 필요합니다.
  - `GET /api/ping`, `POST /api/login` 경우 토큰이 필요 없습니다.
- 각 도메인 Service 경우 비즈니스 로직 체크 목적으로 테스트 코드(Mocking 기반) 작성했습니다.
  - `HolidayServiceTests`
  - `EmployeeServiceTests`

## 엔드포인트
- `GET /api/ping`
  - 설명: Health Check 용도
  - 응답: `pong`
- `POST /api/login`
  - 설명: 사용자 로그인
  - 요청: 
    ```
    BODY (JSON)
    {
      "email" : "kimmy@erphr.com",
      "password" : "abc123123"
    }
    ```
  - 응답: 
    ```
    {
      "expiredAt": "1697553874000",
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjk2OTQ5MDc0LCJleHAiOjE2OTc1NTM4NzR9.AY4nGphdUD5OteL_KK8l-MIcyd6RtSvhXg3A9mJGr-c"
    }
    ```
- `GET /api/employees`
    - 설명: 직원 목록 조회
    - 응답:
      ```
      [
        {
          "employeeId": 2,
          "email": "sam@erphr.com",
          "firstName": "Sam",
          "lastName": "EastWood",
          "phoneNumber": "01012345432",
          "lastLogin": null,
          "joinDate": "2023-08-16",
          "roleName": "leader",
          "divisionName": "software"
        },
        ...
      ]
      ```
- `GET /api/employee/2`
    - 설명: 직원 조회
    - 응답:
      ```
      {
        "employeeId": 2,
        "email": "sam@erphr.com",
        "firstName": "Sam",
        "lastName": "EastWood",
        "phoneNumber": "01012345432",
        "lastLogin": null,
        "joinDate": "2023-08-16",
        "roleName": "leader",
        "divisionName": "software"
      }
      ```
- `POST /api/employees/register`
    - 설명: 직원 등록
    - 요청:
      ```
      BODY (JSON)
      {
        "email" : "john@erphr.com",
        "password" : "abc123123",
        "firstName" : "John",
        "lastName" : "Doe",
        "phoneNumber" : "01012345678",
        "divisionId" : 2,
        "roleId" : 3,
        "joinDate" : "2023-10-10"
      }
      ```
    - 응답:
      ```
      {
        "statusCode": "201",
        "statusMsg": "Employee registered successfully"
      }
      ```
- `GET /api/holidays/all`
    - 설명: 직원 연차 목록 조회. Role Admin, Leader만 조회 가능
    - 응답:
      ```
      [
        {
          "holidayId": 1,
          "employeeId": 1,
          "status": "PENDING",
          "type": "NORMAL",
          "start": "2023-08-21",
          "end": "2023-08-23",
          "count": 3,
          "note": "trips to korea",
          "updatedBy": null
        },
        ...,
        {
         "holidayId": 4,
          "employeeId": 17,
          "status": "APPROVED",
          "type": "NORMAL",
          "start": "2023-10-11",
          "end": "2023-10-13",
          "count": 3,
          "note": null,
          "updatedBy": 3
        }
      ]
      ```
- `GET /api/holidays`
    - 설명: 본인 연차 기록 목록 조회. (JWT에서 본인 조회)
    - 응답:
      ```
      [
        {
         "holidayId": 4,
          "employeeId": 17,
          "status": "APPROVED",
          "type": "NORMAL",
          "start": "2023-10-11",
          "end": "2023-10-13",
          "count": 3,
          "note": null,
          "updatedBy": 3
        }
      ]
      ```
- `POST /api/holidays/add`
    - 설명: 본인 연차 등록. (JWT에서 본인 조회)
    - 요청:
      ```
      BODY (JSON)
      {
        "type" : "NORMAL",
        "start" : "2023-10-11",
        "end" : "2023-10-13"
      }
      ```
    - 응답:
      ```
      {
        "statusCode": "201",
        "statusMsg": "Holiday added successfully"
      }
      ```
- `PATCH /api/holidays/status`
    - 설명: 연차 승인 혹은 거절. Role Admin, Leader만 수정 가능
    - 요청:
      ```
      BODY (JSON)
      {
        "status" : "APPROVED",
        "holidayId" : 4
      }
      ```
    - 응답:
      ```
      {
        "statusCode": "204",
        "statusMsg": "modified successfully"
      }
      ```