Vue.component("home-page",{
    data: function(){
        return{
            manifestacije:null,
            korisnik:null,
            uloga:""
        }
    },
    template: `
    <div>
        <div>
            <manifest-search @clicked="onSearchClick"></manifest-search>
        </div>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" style="margin-left: 50px" >
            <div v-for = "m in manifestacije" v-if="((korisnik===null && m.status==='aktivan')||(uloga==='Administrator')||(uloga!=='Administrator' && m.status==='aktivan'))">
                <div class="row">
                    <div class="card shadow-sm" style="width: 300px">
                        <img :src="'../images/'+m.slika+'.jpg'" width = "200px" heigth = "300">
                        <div class="card-body">
                            <p class="card-text">{{m.naziv}} se odrzava 
                            {{m.datum.date.day}}.{{m.datum.date.month}}.{{m.datum.date.year}}
                            u {{m.datum.time.hour}}:{{m.datum.time.minute}}
                            na lokaciji {{m.lokacija.adresa.ulica}} {{m.lokacija.adresa.broj}}
                            {{m.lokacija.adresa.mesto}} {{m.lokacija.adresa.postBroj}}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type = "button" v-on:click="prikazManifestacije(m)">Detalji</button> 
                                    <div v-if="(korisnik!==null)">
                                        <button  v-if="(korisnik.uloga==='Kupac')" type= "button" class="btn btn-sm btn-outline-secondary" v-on:click="rezervisiKartu(m)">Rezervisi karte</button>
                                    </div>
                                    <div v-if="(korisnik!==null)">
                                        <button  v-if="(korisnik.uloga==='Administrator' && m.status==='neaktivan')" type= "button" class="btn btn-sm btn-outline-secondary" v-on:click="aktivirajManif(m)">Aktiviraj</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,
    methods:{
        prikazManifestacije:function(m){
            axios
            .get(`prikazManif/${m.naziv}`)
            .then(response=>{
                const manif = response.data
                localStorage.setItem('manif',JSON.stringify(manif))
                this.$router.push('/prikazManifestacije')
            })
        },
        rezervisiKartu:function(m){
            localStorage.setItem('manif',JSON.stringify(m));
            this.$router.push('/rezervacija');
        },
        aktivirajManif:function(m){
            axios
            .post(`aktiviraj/${m.naziv}`)
            .then(response=>{
                window.location.reload()
            })
        },
        onSearchClick:function(search){
            console.log(search)
            axios
            .post('/pretragaManif',search)
            .then(response=>{
                this.manifestacije= response.data
            })
        }
    },
    mounted(){
        this.korisnik = JSON.parse(localStorage.getItem('korisnik'))
        if(this.korisnik!=null){
            this.uloga = this.korisnik.uloga
        }
        axios
        .get('manifestacije/getAll')
        .then(response=>{this.manifestacije=response.data;})
        localStorage.removeItem('manif')
    }
})