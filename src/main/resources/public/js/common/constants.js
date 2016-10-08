/**
 * 상수화 리터럴 객체 관리
 */
constants = window.constants || {};

constants.user = {
	ID_EMPTY : "ID_EMPTY",
	ID_INVALID : "ID_INVALID",
	ID_VALID : "ID_VALID",
	PASSWORD_EMPTY : "PASSWORD_EMPTY",
	PASSWORD_INVALID : "PASSWORD_INVALID",
	PASSWORD_VALID : "PASSWORD_VALID",
	PASSWORD_ALPHABET : "PASSWORD_ALPHABET",
	PASSWORD_SPECIAL : "PASSWORD_SPECIAL",
	PASSWORD_NUMBER : "PASSWORD_NUMBER"
};

// 객체 내 데이터 보호
(function() {
	Object.freeze(constants.user);
})();