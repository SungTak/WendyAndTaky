var wat = wat || {};

(function(wat) {
	/**
	 * 데이터 검증을 담당
	 * 필수 : 반드시 constants.js를 함께 include 해야합니다.
	 */
	wat.Validator = function() {
		if (!(this instanceof arguments.callee)) {
			return new wat.Validator();
		}
	}
	
	wat.Validator.prototype.checkId = function(id) {
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
	
	wat.Validator.prototype.checkPassword = function(password) {
		if (password.length == 0) {
			return constants.user.PASSWORD_EMPTY;
		}
		
		var sCheckPassword = /[^A-Za-z0-9!@#$%^&*()]/;
		if (sCheckPassword.test(password)) {
			return constants.user.PASSWORD_INVALID;
		}
		
		var sCheckSpecial = /[^\w\s]/;
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
	
	wat.Validator.prototype.checkEmail = function(email) {
	    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return regex.test(email);
	}
})(wat);