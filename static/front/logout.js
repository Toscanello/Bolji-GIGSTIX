Vue.component("logout",{
	data: function(){
		return{
		}
	},
	
    template:`
        
        <p>Logging out...</p>
       
	`
		,
		methods : {
			
        },
        created(){
           
            localStorage.removeItem('korisnik')	
            window.location.replace('/')
            axios
            .then(response => {
                this.$router.push('/login')
            })
        }
});