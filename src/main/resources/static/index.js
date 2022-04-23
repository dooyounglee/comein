import { jsnGet, jsnPost } from './loadJson.js';
import { menus, statusCd, cardSeg, spinner } from './code.js';

new Vue({
	el: '#notebook',
	/*template: `<div>
		<div class="container">
			<nav-bar/>
			<div class="row">
				<section class="col-6">
					
				</section>
				<aside class="col-6">
				
				</aside>
			</div>
		</div>
	</div>`,*/
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
			type: 'R',
			myR: 0, exW: 0, myW: 0, exR: 0,
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
		watchExchanges() {
			this.initMatching();
		},
		initMatching() {
			this.matchingList = null;
		},
		dataInit() {
			this.type = 'R';
			this.myR = 0;
			this.exW = 0;
			this.myW = 0;
			this.exR = 0;
		},
		addExchange() {
			$('body').append(spinner)
			jsnPost("/exchange/add", {
				userId: this.user._id,
				type: this.type,
				myR: this.myR,
				exW: this.exW,
				myW: this.myW,
				exR: this.exR,
				useYn: 'Y',
			}).then((result) => {
				jsnGet('/exchange/list').then((result1) => {
					this.exchanges = result1.exchanges;
				})
				alert(result.message);
				$('#exampleModal').modal('hide');
				$('#disabledDiv').remove();
			});
			this.dataInit();
		},
		delExchange(id) {
			if(confirm("really delete?")){
				$('body').append(spinner)
				jsnPost("/exchange/del", {
					_id: id,
				}).then((result) => {
					jsnGet('/exchange/list').then((result1) => {
						this.exchanges = result1.exchanges;
						this.$forceUpdate();
						alert(result.message)
						$('#disabledDiv').remove();
					})
				});
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
		exchanges: {
			handler: 'watchExchanges'/*'matchingCount'*/,
		}
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
	/*mounted() {
		$(this.$refs.vuemodal).on("hidden.bs.modal", () => {})
	},*/
})

