var wat = wat || {};

(function(wat) {
	
	wat.File = function() {
		if (!(this instanceof arguments.callee)) {
			return new wat.File();
		}
	}
	
	/**
	 * input file에서 change 이벤트가 발생했을 때,
	 * 본문에 읽은 이미지 파일을 추가해서 보여준다.
	 * 
	 * @param event객체 (file : input file element, content : content 정보를 가진 element)
	 */
	wat.File.prototype.appendContentFile = function(event) {
		var eFile = event.data.file[0];
		var $content = event.data.content;
		
	    if (eFile.files && eFile.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function(e) {
	        	var eImg = $("<img>").prop("src", e.target.result);
	        	$content.append(eImg);
	        }
	        reader.readAsDataURL(eFile.files[0]);
	    }
	}
	
	/**
	 * input file에서 change 이벤트가 발생했을 때,
	 * preview 요소에 읽은 이미지 파일을 미리보기해준다.
	 * 
	 * @param event객체 (file : input file element, preview : image element)
	 */
	wat.File.prototype.previewImage = function(event) {
		var eFile = event.data.file[0];
		var $preview = event.data.preview;
		
		if (eFile.files && eFile.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$preview.prop("src", e.target.result);
			}
			reader.readAsDataURL(eFile.files[0]);
		}
	}
	
})(wat);