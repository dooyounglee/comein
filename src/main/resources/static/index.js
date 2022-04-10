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
				this.frequencies = result.frequencies;
			});
			this.id = '';
			this.showMenuId = 3;
			//jsnGet("/exchange/list").then((result) => this.frequencies = result);
		},
		logout() {
			jsnGet("/users/logout").then();
			this.user = null;
			this.frequencies = null;
			this.showMenuId = 0;
		},
		updateMenus() {
			let tmp_menus = [...menus];
			this.menus = tmp_menus.filter(a => a.isLogin === this.isUser);
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
		/*user: {
			handler: 'isUser',
		},*/
	},
	created() {
		jsnGet("/users/checkSession").then((result) => {
			if(!!result){
				this.user = result;
				this.showMenuId = 3;
			}else{
				this.user = null;
				this.frequencies = null;
				this.showMenuId = 0;
			}
		}).catch(e=>console.log(e));
		/*fetch('http://localhost:8081/exchange/test', null).then(res => {
			return res.json()
		}).then(result => {
			console.log(result);
			this.frequencies = result;
		}).catch(e => {
			console.log(e);
		})*/
		//jsn1("/users/list").then((result) => this.users = result);
		/*let promise1 = jsn1("/users/list");
		promise1.then((result) => {
			this.users = result;
		})*/
		//jsn("/exchange/test1", this.frequencies1);
		
		/*fetch('/exchange/test1', {
			method: 'POST',
			body: JSON.stringify({
				userId: 'userId',
				type: 'type',
			})
		})*/
	},
})

