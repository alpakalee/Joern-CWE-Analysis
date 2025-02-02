val src = cpg.identifier.name("data"); // data 변수를 추적

cpg.call.nameExact(Operators.multiplication).l.filter { mulCall =>
  mulCall.argument(1) // 연산의 첫 번째 인자
    .reachableBy(src) // data 변수가 데이터 흐름을 통해 전달됨
    .whereNot(
      _.ast.isControlStructure // 조건문 내부에서 연산이 수행되는지 확인
      .controlStructureType("IF") // IF 조건문 확인
      .condition.code(".*<=.*MAX.*") // 최대값 검증 여부 확인
    )
    .hasNext // 검증이 없는 경우만 탐색
}
