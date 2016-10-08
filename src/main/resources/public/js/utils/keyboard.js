/**
 * 키보드 관련 이벤트 유틸 
 * 
 * 참고 : http://h5bak.tistory.com/233
 */
function KeyboardUtils() {
	if (!(this instanceof arguments.callee)) {
		return new KeyboardUtils();
	}
}

KeyboardUtils.prototype.checkCapsLock = function(event) {
	var keyCode = event.keyCode;
	var shiftKey = event.shiftKey;
	
	// 소문자 shift키가 안눌러져있음 || 1부터 F11까지 shift키 눌러져있음
	if (((keyCode >= 65 && keyCode <= 90) && shiftKey === false) || 
		(keyCode >= 97 && keyCode <= 122) && shiftKey ) {
		// capsLock이 켜진 경우
		return true;
	}
	return false;
}