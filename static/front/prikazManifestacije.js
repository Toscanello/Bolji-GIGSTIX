Vue.component("prikazmanifestacije",{
    data: function(){
        return{
          m:"",
          korisnik:"",
          komentari:null,
          total:0
        }
    },

    template:`
        <div>
            <img :src="'../images-manifestations/'+m.slika" width = "300px" heigth = "300">
            <div class="card-body">
                <p class="card-text">Naziv: {{m.naziv}}<br/>Datum: {{m.datum.date.day}}.{{m.datum.date.month}}.{{m.datum.date.year}}
                u {{m.datum.time.hour}}:{{m.datum.time.minute}}<br/>
                Lokacija: {{m.lokacija.adresa.ulica}} {{m.lokacija.adresa.broj}}
                {{m.lokacija.adresa.mesto}} {{m.lokacija.adresa.postBroj}}<br/>Broj mesta: {{m.brojMesta}}<br/>
                Cena karte je: {{m.cena}}</p>
            </div>
            <div>
            <komentarisanje @clicked="onCommentClick"> </komentarisanje>
            </div>
            <div>
                <h4>Prosek:</h4>
                {{total}}
            </div>
            <div v-for="k in komentari">
                <div v-if="k.aktivan===true">
                    <p>{{k.tekst}}</br></p>
                </div>
            </div>
        </div>
    `,
    mounted(){
        this.m=JSON.parse(localStorage.getItem('manif'))
        this.korisnik=JSON.parse(localStorage.getItem('korisnik'))
        axios
        .get(`komentari/${this.m.naziv}`)
        .then(response=>{
            console.log(response.data)
            this.komentari=response.data
            var counter = 0
            this.komentari.forEach(element => {
                if(element.aktivan==true){
                    this.total =  this.total+element.ocena;
                    counter=counter+1
                }
            });
            this.total = this.total/counter;
        })
    },
    methods:{
        onCommentClick: function(kom){
            console.log(kom)
            axios
            .post("/komentarisi", {
                komentar:kom.komentar,
                ocena:kom.ocena,
                manifestacija:this.m.naziv,
                korisnik:this.korisnik.username
            })
            .then(this.$router.push('/home-page'))
            .catch(error=>{
                alert("Ne mozete komentarisati ovu manifestaciju jer jos nije prosla ili niste kupili kartu za nju")
                this.$router.push('/home-page')
            })
        }
    }
})