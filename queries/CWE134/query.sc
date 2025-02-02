cpg.call(".*printf") // printf 관련 모든 함수 호출 탐지
  .whereNot(_.argument(1).isLiteral) // 첫 번째 인자가 리터럴 문자열이 아닌 경우만 탐지
  .l // 리스트 형태로 반환
