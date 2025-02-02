cpg.call("execl") // exec 호출 탐지
  .whereNot( // 조건 무형(~를=를) 만족하지 않는 경우만 탐색
    _.repeat(_.cfgPrev) // cfg 상의 exec 호출 이전 노드를 반복 탐색
    .until(_.isCall.name("wcscat")) // wcscat 호출을 발견할 때까지 탐색
  )
  .l
