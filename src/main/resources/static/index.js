import { jsnGet, jsnPost } from './loadJson.js';
import { menus, statusCd, cardSeg } from './code.js';

new Vue({
	el: '#notebook',
	data() {
		return {
			showMenuId: 0,
			menus: [[...menus][0]],
			exchanges: null,
			id: '',
			user: null,
			selectedExchange: null,
			matchingList: null,
			statusCd: statusCd,
			cardSeg: cardSeg,
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
				this.exchanges = result.exchanges;
			});
			this.id = '';
			this.showMenuId = 3;
			
		},
		logout() {
			jsnGet("/users/logout").then();
			this.user = null;
			this.exchanges = null;
			this.showMenuId = 0;
			this.selectedExchange = null;
			this.matchingList = null;
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
		match(exchange) {
			jsnGet("/exchange/matching?_id="+exchange._id).then((result) => {
				this.matchingList = result;
			});
			this.selectedExchange = exchange;
		},
		/*matchingCount() {
			for(let exchange of this.exchanges){
				jsnGet("/exchange/matching?_id="+exchange._id).then((result) => {
					exchange.matchingCount = result.length;
					this.$forceUpdate();
				});
			}
		},*/
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
		/*exchanges: {
			handler: 'matchingCount',
		}*/
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

