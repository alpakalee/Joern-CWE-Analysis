val allForLoops = cpg.controlStructure.controlStructureType("FOR"); // 모든 FOR 루프 탐색

val forWithCount = allForLoops.where(_.condition.code(".*count.*")); 
// count 변수가 포함된 for문만 필터링

val unvalidatedForLoops = forWithCount.whereNot(
  _.repeat(_.cfgNext).until(_.isControlStructure.controlStructureType("IF"))
  .where(_.condition.code(".*count.*>=.*&&.*count.*<=.*"))
);
// count 범위 검사가 없는 경우 필터링

unvalidatedForLoops.l
