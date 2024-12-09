# Directory Structure
기본적으로 데이터의 흐름과 의존성은 presentation -> domain -> storage 이다.
```plaintext
├── presentation                     # Project API Controller
│   ├── build.gradle
│   └── src
├── domain                           # Project Business Logic
│   ├── auth
│   ├── common
│   └── user
└── storage                          # Project Data, JPA
    └── mysql

