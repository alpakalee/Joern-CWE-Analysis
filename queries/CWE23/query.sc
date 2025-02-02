val createFileCalls = cpg.call.name("CreateFileW"); // CreateFileW 호출 탐지

val filePathArguments = createFileCalls.argument(1); 
// CreateFileW의 첫 번째 인수 추적

val userInputs = cpg.call.name("recv"); 
// 데이터 흐름 역추적: 사용자 입력 함수 확인

val vulnerablePaths = filePathArguments.reachableBy(userInputs);

vulnerablePaths.p
