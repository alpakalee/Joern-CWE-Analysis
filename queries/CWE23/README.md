# CWE-23: 상대 경로 탐색 (Relative Path Traversal) - Joern 탐지 실패 분석

## 📌 개요  
CWE-23은 **사용자 입력을 통해 상대 경로가 조작될 경우, 의도하지 않은 파일이나 디렉토리에 접근하는 취약점**입니다.  
이를 악용하면 공격자가 **임의 파일을 읽거나 덮어쓰는 보안 문제**가 발생할 수 있습니다.

## 🕵️‍♂️ Joern 탐지 실패 원인 분석  
현재 Joern 쿼리는 다음과 같은 방식으로 취약점을 탐지하도록 설계되었습니다.  
1. `CreateFileW()` 호출을 찾음  
2. `CreateFileW()`의 첫 번째 인수(파일 경로)를 추적  
3. `recv()`와 같은 사용자 입력을 통해 경로가 조작되는 경우 탐지  

하지만 이번 분석에서는 **Joern이 탐지를 실패하였음**.  
실패 원인은 다음과 같이 분석할 수 있습니다.

### 🔹 실패 원인  
1. **코드 내 `#ifdef` 및 `#endif` 전처리기가 많아 Joern이 정확한 경로를 분석하지 못했을 가능성**  
2. **폴더 체로 분석할 때 Joern이 정상적으로 파일을 포함하지 못했을 가능성**  
3. **데이터 흐름 분석이 `CreateFileW()`와 `recv()` 간의 직접적인 연결을 찾지 못했을 가능성**  

## ✅ 개선 방안  
- **Joern에서 전처리된 코드(`cpp -E` 활용)로 분석하여 탐지 성능을 향상**  
- **Joern 분석 범위를 확장하여 `recv()`를 통해 데이터가 어떻게 흘러가는지 명확히 추적**  
- **사용자 입력이 직접적으로 `CreateFileW()`에 전달되지 않더라도, 중간 변수를 추적하는 쿼리 개선**  