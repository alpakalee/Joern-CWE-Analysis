# Joern-CWE-Analysis

---

### 🛡 **JOERN-CWE-Analysis**
**CWE 취약점을 탐지하기 위한 Joern 기반 코드 분석 프로젝트**

---

## 📌 프로젝트 개요
`JOERN-CWE-Analysis`는 **Joern**을 활용하여 **CWE(Common Weakness Enumeration)** 취약점을 탐지하고 분석하는 프로젝트입니다.  
이 프로젝트는 **SARD 데이터셋**을 기반으로 취약 코드 예제 및 수정된 코드(Good B2G, G2B)를 제공하며,  
Joern 쿼리를 통해 다양한 CWE 취약점을 자동으로 탐지할 수 있도록 설계되었습니다.  

이 저장소는 다음과 같은 기능을 수행합니다:
- CWE별 취약 코드 예제 및 수정 코드 제공
- Joern 쿼리를 활용한 취약점 자동 탐지
- 탐지 결과를 JSON 및 Markdown 리포트로 변환
- 새로운 CWE를 추가하고 분석할 수 있도록 지속적으로 확장 가능

---

## 📂 디렉토리 구조
```
/JOERN-CWE-Analysis
├── docs/                # 프로젝트 문서 및 발표 자료
├── queries/             # Joern 쿼리 및 실행 결과
├── SARD-CWE/            # SARD 데이터셋에서 추출한 CWE별 코드 예제
├── README.md            # 프로젝트 개요 및 사용법
```

---

## 🚀 사용 방법
### 1️⃣ Joern 설치  
이 프로젝트는 [Joern](https://joern.io/)을 기반으로 동작합니다. Joern을 다운로드하고 설치해주세요.
```bash
# Joern 다운로드 및 실행
wget https://github.com/joernio/joern/releases/download/v1.1.0/joern-cli.zip
unzip joern-cli.zip
export PATH=$PATH:$(pwd)/joern-cli
```

### 2️⃣ CWE 탐지 실행
CWE별 Joern 쿼리를 실행하고 취약점을 탐지할 수 있습니다.
```bash
cd queries/
joern --script CWE-476/query.sc
```
모든 CWE에 대한 탐지를 실행하려면:
```bash
bash queries/run_all.sh
```

### 3️⃣ 탐지 결과 확인
쿼리 실행 후 결과는 `results/` 디렉토리에 JSON 형식으로 저장됩니다.  
Markdown 리포트는 `results/report.md`에서 확인할 수 있습니다.

---

## 🛠 기여 방법
새로운 CWE 취약점을 추가하거나 Joern 쿼리를 개선하고 싶다면 다음 가이드라인을 따라 주세요.

1. 저장소를 **포크(Fork)** 합니다.
2. 새로운 CWE 또는 Joern 쿼리를 추가합니다.  
   - `SARD-CWE/`에 취약 코드 예제를 추가합니다.
   - `queries/`에 새로운 Joern 쿼리를 작성합니다.
3. PR(Pull Request)을 생성합니다.

---

## 🔗 참고 자료
- [Joern 공식 문서](https://joern.io/)
- [CWE 공식 목록](https://cwe.mitre.org/)
- [SARD 데이터셋](https://samate.nist.gov/SARD/)

---