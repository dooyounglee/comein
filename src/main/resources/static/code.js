export const menus = [
	{
		menuId: 0,
		menuName: 'login',
		isLogin: false,
	},
	{
		menuId: 1,
		menuName: 'admin',
		isLogin: true,
	},
	{
		menuId: 2,
		menuName: 'mypage',
		isLogin: true,
	},
	{
		menuId: 3,
		menuName: 'frecuency',
		isLogin: true,
	},
];

export const statusCd = {
	"null" : "매칭가능",
	"R" : "요청",//Request
	"S" : "수락",//Success
	"W" : "수락대기",//Wait
	"RS" : "요청 후 매칭성공",//Request
	"RW" : "요청 후 대기",//Request
}

export const cardSeg = {
	"null" : "primary",
	"R" : "secondary",//Request
	"S" : "success",//Success
	"W" : "warning",//Wait
	"RS" : "success",//Request
	"RW" : "warning",//Request
}

export const spinner =
'<div id="disabledDiv" class="modal-backdrop show">'
+ '<div class="d-flex justify-content-center">'
+ '<div class="spinner-border text-success" style="width: 200px; height: 200px; position: fixed; top: 50%; right: 50%;" role="status">'
+ '</div></div></div>';