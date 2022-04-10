import { jsnGet, jsnPost } from './loadJson.js';
const menus = [
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
new Vue({
	el: '#notebook',
	data() {
		return {
			showMenuId: 0,
			menus: [[...menus][0]],
			frequencies: null,
			id: '',
			user: null,
		}
	},
	methods: {
		login() {
			jsnPost("/users/login", {_id: this.id}).then((result) => {
				this.user = result.user;
				this.users = result.users;
				//jsnGet("/exchange/list").then((result) => {
				//	this.frequencies = result;
				//});
				this.frequencies = result.frequencies;
			});
			this.id = '';
			this.showMenuId = 3;
			
		},
		logout() {
			jsnGet("/users/logout").then();
			this.user = null;
			this.frequencies = null;
			this.showMenuId = 0;
		},
		updateMenus() {
			let tmp_menus = [...menus];
			if(!this.user){
				this.menus = tmp_menus.filter(a => a.menuId === 0);
			}else{
				if(this.user.name == 'admin'){
					this.menus = tmp_menus.filter(a => a.menuId != 0);
				}else{
					this.menus = tmp_menus.filter(a => a.isLogin && a.menuId != 1);
				}
			}
		},
	},
	computed: {
		isUser() {
			return !!this.user ? true : false;
		},
	},
	watch: {
		isUser: {
			handler: 'updateMenus',
		},
	},
	created() {
		jsnGet("/users/checkSession").then((result) => {
			if(!!result){
				this.id = result._id;
				this.login();
			}else{
				this.logout();
			}
		}).catch(e=>console.log(e));
	},
})

