# Directory Structure
기본적으로 데이터의 흐름과 의존성은 presentation -> domain -> storage 이다.
<center>
<img src="https://github.com/user-attachments/assets/2b768352-3c22-42a8-89c3-7d48a472b369" alt="Directory Structure"/>
</center>

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

