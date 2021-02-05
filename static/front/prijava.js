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
		<div id = "loginForm1" >
			<form id = "loginForm" method="POST" @submit.prevent = "submitForm">
				<p>
					<label for="uname"><b>Username</b></label>
					<input type="text" v-model="korisnik.username" placeholder="Enter Username" name="uname" required>
				</p>
				<p>
					<label for="psw"><b>Password</b></label>
					<input type="password" v-model="korisnik.password" placeholder="Enter Password" name="psw" required>
				</p>
				<p>
					<button type= "submit" class="btn btn-sm btn-outline-primary">Login</button>
				</p>
			</form>
		</div>
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
				.catch(error=>{
					console.log("Greska.")	
					alert("Uneti nevalidni ili nepostojeći parametri, pokušajte ponovo.")
					window.location.reload()
	
				})
			}
		},
});