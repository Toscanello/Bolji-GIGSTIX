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
            this.$router.push("/home-page")
            window.location.reload()
        }
});