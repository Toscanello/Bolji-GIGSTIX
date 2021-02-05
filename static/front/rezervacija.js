Vue.component("rezervacija",{
    data:function(){
        return{
            manifestacija:"",
            korisnik:"",
            kartaReg:0,
            kartaVip:0,
            kartaFun:0
        }
    },
    template:`
        <div>
            <img :src="'../images/'+manifestacija.slika+'.jpg'" width = "300px" heigth = "300">
            <div class="card-body">
                <p class="card-text">Naziv: {{manifestacija.naziv}}<br/>Datum: {{manifestacija.datum.date.day}}.{{manifestacija.datum.date.month}}.{{manifestacija.datum.date.year}}
                u {{manifestacija.datum.time.hour}}:{{manifestacija.datum.time.minute}}<br/>
                Lokacija: {{manifestacija.lokacija.adresa.ulica}} {{manifestacija.lokacija.adresa.broj}}
                {{manifestacija.lokacija.adresa.mesto}} {{manifestacija.lokacija.adresa.postBroj}}<br/>Broj mesta: {{manifestacija.brojMesta}}<br/>
                Cena karte je: {{manifestacija.cena}}</p>
            </div>
            <form id="reserveForm" method="POST" @submit.prevent = "submitForm">
                <label>Regular karte</label>
                <input type="number" v-model="kartaReg" min="0" max="4"/>

                <label>Vip karte</label>
                <input type="number" v-model="kartaVip" min="0" max="4" />
            
                <label>Fan pit karte</label>
                <input type="number" v-model="kartaFun" min="0" max="4" />  
            
                <button type= "submit">Rezervisi</button>
            </form>
        </div>
    `,
    mounted(){
        this.manifestacija=JSON.parse(localStorage.getItem('manif'))
        this.korisnik = JSON.parse(localStorage.getItem('korisnik'))
    },
    methods:{
        submitForm:function(){
            axios
            .post('/rezervisi',{
                manifestacija:this.manifestacija.naziv,
                korisnik:this.korisnik.username,
                kartaReg:this.kartaReg,
                kartaVip:this.kartaVip,
                kartaFun:this.kartaFun
            })
            .then(response=>{
                const cena = response.data
                alert("Ukupno za uplatu:"+cena)
                this.$router.push('/home-page')
            })
            .catch(error=>{
                alert("Ne mozete naruciti vise od 4 karte ili je manifestacija prosla")
                window.location.reload()

            })
        }
    }
})