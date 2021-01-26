/**
 * 
 */

const Prijava = {template: '<prijavljivanje></prijavljivanje>'}
const HomePage = {template:'<home-page></home-page>'}
const Registracija = {template:'<registracija></registracija>'}
const RegistracijaManifestacije = {template:'<registracijamanifestacije></registracijamanifestacije>'}
const PrikazManifestacije = {template:'<prikazmanifestacije></prikazmanifestacije>'}

const router = new VueRouter({
	mode: 'hash',
	routes: [
		{path:'/login',component: Prijava},
		{path:'/registruj',component: Registracija},
		{path:'/home-page',component: HomePage},
		{path:'/regManifestacije',component: RegistracijaManifestacije},
		{path:'/prikazManifestacije',component: PrikazManifestacije}
	]
});


var app = new Vue({
	router,
	el: '#app'
});