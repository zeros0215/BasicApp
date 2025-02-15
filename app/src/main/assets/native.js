function callPhone(phoneNumber){
    if(window.NativeBridge == undefined){
        return;
    }
    window.NativeBridge.callPhone(phoneNumber)
}


function callSMS(phoneNumber, message) {
    if(window.NativeBridge == undefined){
        alert('SMS를 보낼수 없습니다.')
        return
    }
    window.NativeBridge.callSMS(phoneNumber, message)
}

function callLocationPos(callbackFunc)
{
	if(window.NativeBridge == undefined)
	{
		alert('네이티브 기능 사용불가');
		return;
	}
	window.NativeBridge.callLocationPos(callbackFunc);
}

function receiveLocationPos(lng, lat)
{
	alert(lng + "," +  lat);
}

function callCamera() {
	if(window.NativeBridge == undefined)
	{
		alert('카메라를 사용하실 수 없습니다.');
		return;
	}
	window.NativeBridge.callCamera();
}