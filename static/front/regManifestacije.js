Vue.component("registracijamanifestacije",{
    data: function(){
        return{
            naziv: "",
            tip: "",
            brojMesta:"",
            datum:"",
            cena:"",
            status:"",
            lokacija:"",
            korisnik:""
        }
    },

    template:`
        <form id="registrationForm" method ="POST" @submit.prevent = "submitForm">
            <div>
                <label for="naziv"><b>Naziv Manifestacije</b></label>
                <input type="text" v-model="naziv" placeholder = "Uneti ime manifestacije" required/>
            </div>
            <div>
                <label for="tip"><b>Tip Manifestacije</b></label>
                <input type="text" v-model="tip" placeholder = "Uneti tip manifestacije" required/>
            </div>
            <div>
                <label for="mesta"><b>Broj Mesta</b></label>
                <input type="text" v-model="brojMesta" placeholder = "Uneti broj mesta" required/>
            </div>
            <div>
                <label for="datum"><b>Datum</b></label>
                <input type="text" v-model="datum" placeholder = "Uneti datum manifestacije" required/>
            </div>
            <div>
                <label for="cena"><b>Cena</b></label>
                <input type="text" v-model="cena" placeholder = "Uneti cenu manifestacije" required/>
            </div>
            <div>
                <label for="lokacija"><b>Lokacija</b></label>
                <input type="text" v-model="lokacija" placeholder = "Example(44.58,41.25,4.juli,27,Zrenjanin,23000)" required/>
            </div>
            <div>
                <button type = "submit">Dodaj</button>
            </div>
        </form>
    `,mounted(){
        this.korisnik=JSON.parse(localStorage.getItem('korisnik'))
    },
    methods:{
        submitForm:function(){
            const manif = {
                    naziv: this.naziv,
                    tip: this.tip,
                    brojMesta: this.brojMesta,
                    datum: this.datum,
                    cena: this.cena,
                    status: "neaktivan",
                    lokacija: this.lokacija,
                    korisnik: this.korisnik.username
            }
            axios
            .post('/regManifestacije', manif)
            .then(response=>{
                this.$router.push('/home-page')
            })
        }
    }
})