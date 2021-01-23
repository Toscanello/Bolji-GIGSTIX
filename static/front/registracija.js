Vue.component("registracija",{
    data: function(){
        return{
            ime: "",
            prezime: "",
            username:"",
            password:"",
            pol:"",
            datum:""
        }
    },

    template:`
        <form id="registrationForm" method ="POST" @submit.prevent = "submitForm">
            <div>
                <label for="ime"><b>Ime</b></label>
                <input type="text" v-model="ime" placeholder = "Unesite ime" required/>
            </div>
            <div>
                <label for="uname"><b>Prezime</b></label>
                <input type="text" v-model="prezime" placeholder = "Unesite prezime" required/>
            </div>
            <div>
                <label for="uname"><b>Username</b></label>
                <input type="text" v-model="username" placeholder = "Unesite username" required/>
            </div>
            <div>
                <label for="uname"><b>Password</b></label>
                <input type="password" v-model="password" placeholder = "Unesite password" required/>
            </div>
            <div>
                <label for="uname"><b>Pol</b></label>
                <input type="text" v-model="pol" required/>
            </div>
            <div>
                <label for="uname"><b>Datum</b></label>
                <input type="text" v-model="datum" required/>
            </div>
            <div>
                <button type = "submit">Registruj</button>
            </div>
        </form>
    `,
    methods:{
        submitForm:function(){
            const user = {
                username:this.username,
                password:this.password,
                ime: this.ime,
                prezime: this.prezime,
                pol:this.pol,
                datum:this.datum,
                uloga:"kupac"
            }
            axios
            .post('/registruj',user)
            .then(response=>{
                const kor = response.data
                console.log(kor)
                localStorage.setItem('korisnik', JSON.stringify(kor))
                this.$router.push('/login')
            })
        }
    }
})