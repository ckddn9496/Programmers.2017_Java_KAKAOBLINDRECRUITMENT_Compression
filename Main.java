import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
//		String msg = "KAKAO";
//		// [11, 1, 27, 15]
		
		String msg = "TOBEORNOTTOBEORTOBEORNOT";
		// [20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34]
		
		
//		String msg = "ABABABABABABABAB";
//		//[1, 2, 27, 29, 28, 31, 30]
		
		
		System.out.println(Arrays.toString(new Solution().solution(msg)));
	}

}

class Solution {
	public int[] solution(String msg) {
		List<Integer> answer = new LinkedList<>();
		
		HashMap<String, Integer> dict = new HashMap<>();
		int dictIdx = 1;
		// init dict
		for (char c = 'A'; c <= 'Z'; c++) {
			dict.put(Character.toString(c), dictIdx++);
		}

		int strIdx = 0;
		while (strIdx < msg.length()) {

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
		}

		int[] result = new int[answer.size()];
		for (int i = 0; i < answer.size(); i++) {
			result[i] = answer.get(i);
		}
		
		return result;
	}
}