Vue.component("edit",{
    data:function(){
        return{
            korisnik:""
        }
    },
    template:`
    <form id="editForm" method ="POST" @submit.prevent = "submitForm">
        <div>
            <label for="ime"><b>Ime</b></label>
            <input type="text" v-model="korisnik.ime" required/>
        </div>
        <div>
            <label for="uname"><b>Prezime</b></label>
            <input type="text" v-model="korisnik.prezime"  required/>
        </div>
        <div>
            <label for="uname"><b>Password</b></label>
            <input type="password" v-model="korisnik.password" required/>
        </div>
        <div>
            <label for="uname"><b>Pol</b></label>
            <input type="text" v-model="korisnik.pol" required/>
        </div>
        <div>
            <label for="uname"><b>Datum</b></label>
            <input type="text" v-model="korisnik.datum"required/>
        </div>
        <div>
            <button type = "submit" class="btn btn-sm btn-outline-primary">Save</button>
        </div>
    </form>
    `,
    mounted(){
        this.korisnik = JSON.parse(localStorage.getItem('korisnik'))
    },
    methods:{
        submitForm:function(){
            axios
            .post('/edit',this.korisnik)
            .then(response=>{
                localStorage.setItem('korisnik',JSON.stringify(this.korisnik))
                this.$router.push('/home-page')
                window.location.reload()
            })
            .catch(error=>{
                console.log("Greska.")	
                alert("Lose unet pol. Mora biti MUSKI ili ZENSKI!")
                window.location.reload()

            })
        }
    }
})