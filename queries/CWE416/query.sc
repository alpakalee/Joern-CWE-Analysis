def free = cpg.call("free"); // free() 호출 탐지
free
  .postDominatedBy(_.ast.isIdentifier) // free 이후 실행되는 코드 분석
  .code(cpg.call.argument(1).code.next) // free(ptr) 이후 ptr이 다시 사용됨 확인
  .l // 리스트로 출력
