/**
 * 데이터 검증을 담당
 * 필수 : 반드시 constants.js를 함께 include 해야합니다.
 */
function Validator() {
	if (!(this instanceof arguments.callee)) {
		return new Validator();
	}
}

Validator.prototype.checkId = function(id) {
	if (id.length == 0) {
		return constants.user.ID_EMPTY;
	}
	
	// id는 영문, 숫자로만 구성이 가능하다.
	var sCheckId = /[^A-Za-z0-9]/;
	if (sCheckId.test(id)) {
		return constants.user.ID_INVALID;
	}
	return constants.user.ID_VALID;
}

Validator.prototype.checkPassword = function(password) {
	if (password.length == 0) {
		return constants.user.PASSWORD_EMPTY;
	}
	
	var sCheckPassword = /[^A-Za-z0-9!@#$%^&*()]/;
	if (sCheckPassword.test(password)) {
		return constants.user.PASSWORD_INVALID;
	}
	
	var sCheckSpecial = /[!@#$%^&*()]/;
	if (sCheckSpecial.test(password) == false) {
		return constants.user.PASSWORD_SPECIAL;
	}
	
	var sCheckAlphabet = /[A-Za-z]/;
	if (sCheckAlphabet.test(password) == false) {
		return constants.user.PASSWORD_ALPHABET;
	}
	
	var sCheckNumber = /[0-9]/;
	if (sCheckNumber.test(password) == false) {
		return constants.user.PASSWORD_NUMBER;
	}
	return constants.user.PASSWORD_VALID;
}