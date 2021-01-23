/**
 * 
 */

const Prijava = {template: '<prijavljivanje></prijavljivanje>'}
const HomePage = {template:'<home-page></home-page>'}
const Registracija = {template:'<registracija></registracija>'}

const router = new VueRouter({
	mode: 'hash',
	routes: [
		{path:'/login',component: Prijava},
		{path:'/registruj',component: Registracija},
		{path:'/home-page',component: HomePage}
	]
});


var app = new Vue({
	router,
	el: '#app'
});