# CWE-23: 상대 경로 탐색 (Relative Path Traversal)

## 📌 개요  
CWE-23은 **입력값에 의해 파일 경로나 리소스 경로가 조작될 수 있어**,  
공격자가 의도하지 않은 파일이나 디렉토리에 접근할 수 있는 취약점입니다.  
이를 악용하면 중요한 시스템 파일을 읽거나, 승인되지 않은 디렉토리로 접근하는 등의 보안 위협이 발생할 수 있습니다.

## 🛠 주요 원인  
- 입력값 검증 미흡  
- 상대 경로 사용 (예: `../../etc/passwd`)  

## 📂 관련 파일  
이 디렉토리에는 CWE-23을 설명하는 예제 코드가 포함되어 있습니다.

| 파일명 | 설명 |  
|--------|------|  
| `CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84.h` | CWE-23 취약점 처리 클래스 정의 및 초기화 헤더 |  
| `CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84a.cpp` | 데이터 초기화 및 객체 생성 후, CWE-23 취약점을 포함한 흐름 시작 |  
| `CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_bad.cpp` | **소켓으로 데이터를 읽고, 경로 검증 없이 파일을 생성하여 CWE-23 발생** |  
| `CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_goodG2B.cpp` | 고정된 파일 이름을 사용해 CWE-23 취약점 방지 |  

---

## 🚨 취약 코드 (BadSink)  
📌 **발생 위치**: `CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_bad.cpp`  
📌 **줄 번호**: `CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_bad::~CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_bad()`

아래 코드에서는 네트워크 소켓을 통해 데이터를 입력받은 후,  
**입력값 검증 없이 파일 경로에 사용하여 CWE-23 취약점(Relative Path Traversal)이 발생**할 수 있습니다.

```c
...

CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_bad::~CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_bad()
{
    {
        HANDLE hFile;
        /* POTENTIAL FLAW: Possibly creating and opening a file without validating the file name or path */
        hFile = CreateFileW(data,
                            (GENERIC_WRITE|GENERIC_READ),
                            0,
                            NULL,
                            OPEN_ALWAYS,
                            FILE_ATTRIBUTE_NORMAL,
                            NULL);
        if (hFile != INVALID_HANDLE_VALUE)
        {
            CloseHandle(hFile);
        }
    }
}

...
```

📌 **문제점**:  
- `data`가 네트워크에서 받은 값이며, **경로 검증 없이** `CreateFileW()` 함수에 사용됨  
- 공격자가 입력값을 `../../etc/passwd` 또는 `C:\Windows\System32\cmd.exe`와 같이 설정하면,  
  **임의의 파일을 열거나 생성하는 보안 문제 발생**  
- **시스템 중요 파일이 유출되거나 덮어씌워질 위험 존재**  

---

## ✅ 개선 코드 (GoodSource - G2B)  
📌 **발생 위치**: `CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_goodG2B.cpp`  
📌 **줄 번호**: `static void goodG2B()`

📌 **설명**:  
G2B(**Good Source to Bad Sink**) 방식에서는 입력값을 **고정된 안전한 파일 경로로 설정**하여,  
상대 경로 탐색 취약점이 발생하지 않도록 방지합니다.

```c
...

static void goodG2B()
{
    wchar_t * data;
    wchar_t dataBuffer[FILENAME_MAX] = BASEPATH;
    data = dataBuffer;
    /* FIX: Use a fixed file name */
    wcscat(data, L"file.txt");
    CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_goodG2B * goodG2BObject = new CWE23_Relative_Path_Traversal__wchar_t_listen_socket_w32CreateFile_84_goodG2B(data);
    delete goodG2BObject;
}

...
```

📌 **개선점**:  
- `data` 값을 **고정된 경로(BASEPATH)와 파일명("file.txt")로 설정**  
- **사용자 입력을 받지 않고, 코드 내부에서 경로를 결정하여 보안 강화**  
- 프로그램이 예측 가능한 방식으로 동작하도록 설계하여 **경로 탐색 공격 방지**  
