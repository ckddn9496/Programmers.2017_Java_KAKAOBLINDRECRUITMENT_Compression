# Programmers.2017_Java_KAKAOBLINDRECRUITMENT_Compression

## 프로그래머스 > (2017) KAKAO BLIND RECRUITMENT > [3차] 압축

### 1. 문제설명

문제: https://programmers.co.kr/learn/courses/30/lessons/17684

input으로 압축하고자 하는 문자열 String msg를 받아온다. 무손실 압축인 LZW압축을 이용하여 문자를 압축하기로 한다. LZW 압축의 과정은 다음과 같다.
  >
    1.  길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
    2.  사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
    3.  w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
    4.  입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
    5.  단계 2로 돌아간다.

**ex) 입력: `KAKAO`**

1. 현재 사전에는 `KAKAO`의 첫 글자 `K`는 등록되어 있으나, 두 번째 글자까지인 `KA`는 없으므로, 첫 글자 `K`에 해당하는 색인 번호 11을 출력하고, 다음 글자인 `A`를 포함한 `KA`를 사전에 27 번째로 등록한다.

2. 두 번째 글자 `A`는 사전에 있으나, 세 번째 글자까지인 `AK`는 사전에 없으므로, `A`의 색인 번호 1을 출력하고, `AK`를 사전에 28 번째로 등록한다.

3. 세 번째 글자에서 시작하는 `KA`가 사전에 있으므로, `KA`에 해당하는 색인 번호 27을 출력하고, 다음 글자 `O`를 포함한 `KAO`를 29 번째로 등록한다.
마지막으로 처리되지 않은 글자 `O`에 해당하는 색인 번호 15를 출력한다.

주어진 문자열을 압축한 후의 사전 색인 번호를 배열로 return하는 문제.

### 2. 풀이

사전을 HashMap<String, Integer>로 선언하여 Key에는 사전문자열을, Value에는 사전색인 번호를 저장하였다. dictIdx는 이후 사전에 단어를 추가할 때의 인덱스 역할을 한다.
```java
HashMap<String, Integer> dict = new HashMap<>();
int dictIdx = 1;
// init dict
for (char c = 'A'; c <= 'Z'; c++) {
  dict.put(Character.toString(c), dictIdx++);
}
```

압축을 위해 주어진 문자열을 처음부터 탐색하기 위해 index를 두고, index가 주어진 문자열의 끝까지 도달할때까지 압축을 진행한다.
```java
int strIdx = 0;
while (strIdx < msg.length()) {
  // implements...
}
```

사전에 있는 단어중 압축해야할 문자열의 접두사이며 가장 긴 단어를 찾아 그 단어와 단어의 길이를 저장하고, 현재 단어 `w`와 다음 문자 `c`를 찾아 출력은 `w`로, 사전에 추가할 단어는 `w+c`로 dictIdx값을 갖도록 저장하며 while문을 수행한다. 문자열 msg의 끝까지 압축이 완료됬다면 `c`가 존재하지 않으므로 이경우에 while문을 탈출시킨다. 

```java
int maxMatchLen = 0;
int output = 0;

// 남은 압축해야할 문자열
String leftStr = msg.substring(strIdx);
String w = "";
// dict에서 가장 길면서 일치하는 문자를 찾는다.
for (Iterator<String> it = dict.keySet().iterator(); it.hasNext(); ) {
  String startStr = it.next();
  if (leftStr.startsWith(startStr) && maxMatchLen < startStr.length()) {
    w = startStr;
    maxMatchLen = startStr.length();
    output = dict.get(startStr);
  }
}

String c = "";
if (strIdx + maxMatchLen < msg.length()) {
  c = Character.toString(msg.charAt(strIdx + maxMatchLen));
  answer.add(output);
  dict.put(w+c, dictIdx++);
  // 사전추가
} else {
  answer.add(output);
  break;
}
strIdx += maxMatchLen;
```

위 작업을 수행하여 만들어진 출력값 answer를 int[]로 만들어서 return한다.

### 3. 배운점

String객체의 `startWith(String prefix)`을 사용해 검사시 prefix가 검사하려는 문자열 보다 길 경우는 무조건 false 리턴한다.
