/**
 * 
 */
Vue.component("prijavljivanje",{
	data: function(){
		return{
			korisnik:{
				username : null,
				password : null
			}
		}
	},
	
	template:`
		<form id = "loginForm" method="POST" @submit.prevent = "submitForm">
			<label for="uname"><b>Username</b></label>
			<input type="text" v-model="korisnik.username" placeholder="Enter Username" name="uname" required>

			<label for="psw"><b>Password</b></label>
			<input type="password" v-model="korisnik.password" placeholder="Enter Password" name="psw" required>

			<button type= "submit">Login</button>
		</form>
	`
		,
		methods : {
			submitForm :function(){
				axios
				.post('/login',{
					username:this.korisnik.username,
					password:this.korisnik.password
				})
				.then(response => {
					localStorage.setItem('korisnik',JSON.stringify(response.data))
					this.$router.push('/home-page')
					window.location.reload()
				})
			}
		},
});