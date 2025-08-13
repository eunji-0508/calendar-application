# **API 명세서**

---

### [ 회원 가입 API ]

* **URL** : POST /signup
* **인증/인가** : 없음
* **데이터 형식** : JSON

### Request
```json
{
  "username": "홍길동",
  "email": "hong@gmail.com",
  "password": "1111"
}
```

### Response

200 OK

회원 가입이 완료되었습니다.

<br  >

---
### [ 로그인 API ]

* **URL** : POST /login
* **인증/인가** : 없음 (로그인 세션 발급)
* **데이터 형식** : JSON

### Request
``` json
{
"email" : "hong@gmail.com",
"password" : "1111"
}
```

### Response
* 200 OK

해당 User로 로그인이 완료되었습니다.

<br  >

---
### [ 로그아웃 API ]

* **URL** : POST /logout
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : 없음

### Request
* Body 없음
* 로그인 후 발급받은 쿠키 포함

### Response
* 200 OK
* Body 없음

<br  >

---
### [ 전체 유저 조회 API ]

* **URL** : GET /users
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : JSON

### Request
* Body 없음

### Response
* 200 OK
``` json
[
    {
        "id": 1,
        "username": "홍길동",
        "email": "hong@gmail.com",
        "createdAt": "2025-08-13T19:56:37.697225",
        "modifiedAt": "2025-08-13T19:56:37.697225"
    },
    {
        "id": 2,
        "username": "심청이",
        "email": "sim@gmail.com",
        "createdAt": "2025-08-13T19:57:39.22592",
        "modifiedAt": "2025-08-13T19:57:39.22592"
    }
]
```

<br  >

---
### [ 유저 단건 조회 API ]

* **URL** : GET /users/{userId}
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : JSON

### Request
* Path Variable : userId

### Response
* 200 OK
``` json
{
    "id": 1,
    "username": "홍길동",
    "email": "hong@gmail.com",
    "createdAt": "2025-08-13T19:56:37.697225",
    "modifiedAt": "2025-08-13T19:56:37.697225"
}
```

<br  >

---

### [ 유저 수정 API ]

* **URL** : PATCH /users/{userId}
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : JSON

### Request
``` json
{
    "username" : "홍길동2",
    "email" : "hong2@gmail.com"
}
```

### Response
* 200 OK
``` json
{
    "id": 1,
    "username": "홍길동2",
    "email": "hong2@gmail.com",
    "createdAt": "2025-08-13T19:56:37.697225",
    "modifiedAt": "2025-08-13T19:56:37.697225"
}
```

<br  >

---
### [ 유저 삭제 API ]

* **URL** : DELETE /users/{userId}
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : 없음

### Request
* Path Variable : userId

### Response
* 200 OK
* 바디 없음

<br  >

---
### [ 일정 생성 API ]

* **URL** : POST /users/{userId}/calendars
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : JSON

### Request
``` json
{
"title" : "의적 활동",
"content" : "북쪽가서 의적 활동하기"
}
```

### Response
* 200 OK
``` json
{
    "id": 1,
    "title": "의적 활동",
    "content": "북쪽가서 의적 활동하기",
    "createdAt": "2025-08-13T19:48:48.3483554",
    "modifiedAt": "2025-08-13T19:48:48.3483554"
}
```

<br  >

---
### [ 전체 일정 조회 API ]

* **URL** : GET /users/{userId}/calendars
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : JSON

### Request
* Path Variable : userId

### Response
* 200 OK
``` json
[
    {
        "id": 1,
        "title": "의적 활동",
        "content": "남쪽가서 의적 활동하기",
        "createdAt": "2025-08-13T20:15:18.985833",
        "modifiedAt": "2025-08-13T20:15:18.985833"
    },
    {
        "id": 2,
        "title": "의적 활동",
        "content": "북쪽가서 의적 활동하기",
        "createdAt": "2025-08-13T20:15:31.385998",
        "modifiedAt": "2025-08-13T20:15:31.385998"
    },
    {
        "id": 3,
        "title": "의적 활동",
        "content": "동쪽가서 의적 활동하기",
        "createdAt": "2025-08-13T20:15:43.773113",
        "modifiedAt": "2025-08-13T20:15:43.773113"
    },
    {
        "id": 4,
        "title": "의적 활동",
        "content": "서쪽가서 의적 활동하기",
        "createdAt": "2025-08-13T20:15:48.256119",
        "modifiedAt": "2025-08-13T20:15:48.256119"
    }
]
```

<br  >

---
### [ 일정 단건 조회 API ]

* **URL** : GET /users/{userId}/calendars/{calendarId}
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : JSON

### Request
* Path Variable : userId, calendarId

### Response
* 200 OK
``` json
{
    "id": 1,
    "title": "의적 활동",
    "content": "남쪽가서 의적 활동하기",
    "createdAt": "2025-08-13T20:15:18.985833",
    "modifiedAt": "2025-08-13T20:15:18.985833"
}
```
<br  >

---
### [ 일정 수정 API ]

* **URL** : PATCH /users/{userId}/calendars/{calendarId}
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : JSON

### Request
```json
{
  "title" : "탐관오리 소탕",
  "content" : "남쪽으로 가서 탐관오리 혼내주기"
}
```

### Response

200 OK
``` json
{
    "id": 1,
    "title": "탐관오리 소탕",
    "content": "남쪽으로 가서 탐관오리 혼내주기",
    "createdAt": "2025-08-13T20:15:18.985833",
    "modifiedAt": "2025-08-13T20:15:18.985833"
}
```
<br  >

---
### [ 일정 삭제 API ]

* **URL** : DELETE /users/{userId}/calendars/{calendarId}
* **인증/인가** : 로그인 필요 (세션 기반)
* **데이터 형식** : 없음

### Request
* Path Variable : userId, calendarId

### Response
* 200 OK
* 바디 없음

<br >

---
## [ 전체 API 요약 ]
| API명           | HTTP 메서드 | URL                                      | 인증/검증   | 주요 설명                             |
|----------------|------------|-----------------------------------------|------------|------------------------------------|
| 회원 가입        | POST       | `/signup`                                | 없음        | `username`, `email`, `password` 필수 |
| 로그인          | POST       | `/login`                                 | 없음        | 로그인 후 세션 쿠키 발급               |
| 로그아웃        | POST       | `/logout`                                | 로그인 필요 | 세션 무효화                           |
| 전체 유저 조회    | GET        | `/users`                                 | 로그인 필요 | 모든 유저 조회                         |
| 단일 유저 조회    | GET        | `/users/{userId}`                        | 로그인 필요 | 특정 유저 조회                         |
| 유저 수정        | PATCH      | `/users/{userId}`                        | 로그인 필요 | username, email 수정 가능              |
| 유저 삭제        | DELETE     | `/users/{userId}`                        | 로그인 필요 | 해당 유저 삭제                         |
| 일정 생성        | POST       | `/users/{userId}/calendars`              | 로그인 필요 | 제목, 내용 필수                        |
| 전체 일정 조회    | GET        | `/users/{userId}/calendars`              | 로그인 필요 | 유저별 전체 일정 조회                   |
| 단일 일정 조회    | GET        | `/users/{userId}/calendars/{calendarId}` | 로그인 필요 | 특정 일정 조회                         |
| 일정 수정        | PATCH      | `/users/{userId}/calendars/{calendarId}` | 로그인 필요 | 제목, 내용 수정, `modifiedAt` 갱신     |
| 일정 삭제        | DELETE     | `/users/{userId}/calendars/{calendarId}` | 로그인 필요 | 해당 일정 삭제                         |

<br  >

---
## [ ERD ]


