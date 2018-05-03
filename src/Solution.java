
import java.util.Stack;

public class Solution {
	
	private static char OPEN_BRACKET = '<';
	private static char CLOSE_TAG_MARKER = '/';
	private static char END_BRACKET = '>';
	
	private static char NO_MATCH_TAG = '#';
	
	private enum TAG_STATUS {NONE, OPEN_TAG, CLOSE_TAG};
	
	private static String ERROR_FORMAT_NOTMATCH = "Expected %s found %s";
	
	private static String SUCCESS_MSG = "Correctly tagged paragraph";
	
	private static String CLOSE_TAG_FORMAT = "</%s>";
	
	public String checkTag(String input) {
		
		String msg = "";
		
		Stack<Character> tagList = new Stack<>();
		TAG_STATUS status = TAG_STATUS.NONE;
		
		char currentTag = ' ';
		
		for(int i=0; i<input.length(); i++){
			
			char currentChar = input.charAt(i);
			
			switch(status){
			
			case NONE:
				if(currentChar == OPEN_BRACKET){
					status = TAG_STATUS.OPEN_TAG;
				}
				break;
			case OPEN_TAG:
				if(currentChar == CLOSE_TAG_MARKER){
					status = TAG_STATUS.CLOSE_TAG;
//				} else if(currentChar == OPEN_BRACKET){  //if <<A></A> or <A<A></A> is accepted else comment this out
//					currentTag = ' ';
				} else if (currentChar == END_BRACKET) {
					if(currentTag != ' '){
						tagList.push(currentTag);
					}
					currentTag = ' ';
					status = TAG_STATUS.NONE;
				} else {
					if(currentTag == ' ' && Character.isUpperCase(currentChar)){
						currentTag = currentChar;
					} else {
						currentTag = ' ';
						status = TAG_STATUS.NONE;
					}
				}
				
				break;
			case CLOSE_TAG:
				if(currentChar == END_BRACKET){
					if(!tagList.isEmpty()){
						char popTag = tagList.pop();
						if(popTag != currentTag){
							msg = formErrorMessage(popTag, currentTag);
						} else {
							currentTag = ' ';
							status = TAG_STATUS.NONE;
						}
					} else {
						msg = formErrorMessage(NO_MATCH_TAG, currentTag);
					}
				} else {
					if(currentTag == ' ' && Character.isUpperCase(currentChar)){
						currentTag = currentChar;
					} else {
						currentTag = ' ';
						status = TAG_STATUS.NONE;
					}
				}
				break;
			
			}
			
		}
		
		if(msg.isEmpty()){
			if(!tagList.isEmpty()){
				msg = formErrorMessage(tagList.pop(),NO_MATCH_TAG);
			} else {
				msg = SUCCESS_MSG;
			}
		}
	
		return msg;
		
	}
	
	private String formErrorMessage(char expectedTag, char unexpectedTag){
		
		String expectedTagFormat;
		if(expectedTag != NO_MATCH_TAG){
			expectedTagFormat = String.format(CLOSE_TAG_FORMAT, expectedTag);
		} else {
			expectedTagFormat = String.valueOf(expectedTag);
		}
		
		String unexpectedTagFormat;
		if(unexpectedTag != NO_MATCH_TAG){
			unexpectedTagFormat = String.format(CLOSE_TAG_FORMAT, unexpectedTag);
		} else {
			unexpectedTagFormat = String.valueOf(unexpectedTag);
		}
		
		String errorMessage = String.format(ERROR_FORMAT_NOTMATCH, expectedTagFormat, unexpectedTagFormat);
		
		return errorMessage;
	}
	
	
}
