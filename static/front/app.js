/**
 * 
 */

const Prijava = {template: '<prijavljivanje></prijavljivanje>'}
const HomePage = {template:'<home-page></home-page>'}

const router = new VueRouter({
	mode: 'hash',
	routes: [
		{path:'/',component: Prijava},
		{path:'/home-page',component: HomePage}
	]
});


var app = new Vue({
	router,
	el: '#prijava'
});