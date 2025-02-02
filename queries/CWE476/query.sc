val nullAssignments = cpg.assignment // 모든 변수 할당 노드 탐지
  .filter(_.code.matches(".*= NULL.*")) // NULL로 초기화된 모든 변수
  .target // 할당문의 왼쪽에 있는 변수를 타겟으로
  .isIdentifier // Identifier 노드만 선택(식별자)

val dereferences = cpg.call // 모든 함수 호출 노드 탐지
  .filter(_.code.matches(".*\\*.*")) // 포인터(*)가 들어간 호출 필터링
  .argument // 호출에 사용된 인자
  .isIdentifier // 인자가 Identifier 노드인지 확인
  .filter(a => nullAssignments.l.map(_.name).contains(a.name)) // 변수 이름 비교

dereferences.l // 출력