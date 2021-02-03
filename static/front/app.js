/**
 * 
 */

const Prijava = {template: '<prijavljivanje></prijavljivanje>'}
const HomePage = {template:'<home-page></home-page>'}
const Registracija = {template:'<registracija></registracija>'}
const RegistracijaManifestacije = {template:'<registracijamanifestacije></registracijamanifestacije>'}
const PrikazManifestacije = {template:'<prikazmanifestacije></prikazmanifestacije>'}
const KorisniciPregled = {template:'<korisnicipregled></korisnicipregled>'}
const Logout = {template:'<logout></logout>'}
const Rezervacija = {template:'<rezervacija></rezervacija>'}
const Edit = {template:'<edit></edit>'}
const Karte = {template:'<karte></karte>'}

const router = new VueRouter({
	mode: 'hash',
	routes: [
		{path:'/login',component: Prijava},
		{path:'/registruj',component: Registracija},
		{path:'/home-page',component: HomePage},
		{path:'/regManifestacije',component: RegistracijaManifestacije},
		{path:'/prikazManifestacije',component: PrikazManifestacije},
		{path:'/korisniciPregled',component: KorisniciPregled},
		{path:'/logout',component: Logout},
		{path:'/rezervacija',component:Rezervacija},
		{path:'/edit', component: Edit},
		{path:'/karte', component: Karte}
		
	]
});


var app = new Vue({
	router,
	el: '#app'
});